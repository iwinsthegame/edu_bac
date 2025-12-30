package com.rk.edu.services.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rk.edu.dto.request.*;
import com.rk.edu.dto.response.*;
import com.rk.edu.enums.AttemptStatus;
import com.rk.edu.model.*;
import com.rk.edu.repositories.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentAttemptService {

    private final MockTestRepository mockTestRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final UserRepository userRepository;
    private final StudentAttemptRepository studentAttemptRepository;
    private final AttemptQuestionRepository attemptQuestionRepository;

    // -------------- START ATTEMPT --------------
    @Transactional
    public StartAttemptResponseDTO startAttempt(Long mockTestId, Long studentId) {

        MockTest mock = mockTestRepository.findById(mockTestId)
                .orElseThrow(() -> new RuntimeException("MockTest not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // create attempt
        StudentAttempt attempt = StudentAttempt.builder()
                .student(student)
                .mockTest(mock)
                .startTime(LocalDateTime.now())
                .status(AttemptStatus.STARTED)
                .totalQuestions(mock.getQuestions().size())
                .build();

        StudentAttempt saved = studentAttemptRepository.save(attempt);

        // create blank AttemptQuestion rows (optional) -- can be created on saveAnswer instead.
        // We'll not precreate; frontend will fetch questions and create attemptQuestion when user answers.

        // prepare response: send questions and options but hide correct flags
        List<QuestionForAttemptDTO> qdto = mock.getQuestions().stream()
                .map(q -> {
                    QuestionForAttemptDTO qf = new QuestionForAttemptDTO();
                    qf.setQuestionId(q.getId());
                    qf.setQuestionText(q.getQuestionText());
                    qf.setQuestionType(q.getQuestionType());
                    qf.setSubject(q.getSubject());
                    List<OptionForAttemptDTO> opts = q.getOptions().stream()
                            .map(o -> {
                                OptionForAttemptDTO od = new OptionForAttemptDTO();
                                od.setOptionId(o.getId());
                                od.setOptionKey(o.getOptionKey());
                                od.setOptionText(o.getOptionText());
                                return od;
                            }).collect(Collectors.toList());
                    qf.setOptions(opts);
                    return qf;
                }).collect(Collectors.toList());

        StartAttemptResponseDTO resp = new StartAttemptResponseDTO();
        resp.setAttemptId(saved.getId());
        resp.setMockTestId(mock.getId());
        resp.setMockTestTitle(mock.getTitle());
        resp.setDurationMinutes(mock.getDurationMinutes());
        resp.setQuestions(qdto);

        return resp;
    }

    // -------------- SAVE ANSWER --------------
    @Transactional
    public void saveAnswer(Long attemptId, Long studentId, SaveAnswerRequestDTO req) {

        StudentAttempt attempt = studentAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (!attempt.getStudent().getId().equals(studentId)) {
            throw new RuntimeException("Not allowed");
        }
        if (attempt.getStatus() != AttemptStatus.STARTED) {
            throw new RuntimeException("Attempt is not active");
        }

        // find question
        Question question = questionRepository.findById(req.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // find existing attemptQuestion or create new
        AttemptQuestion aQ = attemptQuestionRepository
                .findByAttemptIdAndQuestionId(attemptId, question.getId())
                .orElseGet(() -> AttemptQuestion.builder()
                        .attempt(attempt)
                        .question(question)
                        .build());

        // store selected option ids as CSV
        String csv = (req.getSelectedOptionIds() == null || req.getSelectedOptionIds().isEmpty())
                ? ""
                : req.getSelectedOptionIds().stream().map(String::valueOf).collect(Collectors.joining(","));

        aQ.setSelectedOptionIds(csv);
        aQ.setTimeSpentSeconds(req.getTimeSpentSeconds());
        // do not evaluate correctness here (optional); evaluation is done at submission
        attemptQuestionRepository.save(aQ);
    }

    // -------------- SUBMIT ATTEMPT --------------
    @Transactional
    public SubmitAttemptResponseDTO submitAttempt(Long attemptId, Long studentId) {

        StudentAttempt attempt = studentAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (!attempt.getStudent().getId().equals(studentId)) {
            throw new RuntimeException("Not allowed");
        }
        if (attempt.getStatus() != AttemptStatus.STARTED) {
            throw new RuntimeException("Attempt already submitted or finished");
        }

        // fetch all questions for this mock
        List<Question> questions = attempt.getMockTest().getQuestions();
        int totalQ = questions.size();

        List<AttemptQuestion> answers = attemptQuestionRepository.findByAttemptId(attemptId);

        Map<Long, AttemptQuestion> answerMap = answers.stream()
                .collect(Collectors.toMap(a -> a.getQuestion().getId(), a -> a));

        int correct = 0;
        int wrong = 0;
        int unattempted = 0;
        int totalMarks = 0;

        // scoring rules: assume 1 mark per question, no negative marking.
        int marksPerQuestion = 1;

        for (Question q : questions) {
            AttemptQuestion a = answerMap.get(q.getId());
            if (a == null || a.getSelectedOptionIds() == null || a.getSelectedOptionIds().isBlank()) {
                unattempted++;
            } else {
                // parse selected option ids
                Set<Long> userSelected = Arrays.stream(a.getSelectedOptionIds().split(","))
                        .filter(s -> !s.isBlank())
                        .map(Long::valueOf)
                        .collect(Collectors.toSet());

                // compute correct option ids
                Set<Long> correctOptionIds = q.getOptions().stream()
                        .filter(Option::getIsCorrect)
                        .map(Option::getId)
                        .collect(Collectors.toSet());

                // For MCQ require exact match (single); for MULTI_SELECT require set equality
                boolean isCorrect = userSelected.equals(correctOptionIds);

                a.setIsCorrect(isCorrect);
                attemptQuestionRepository.save(a);

                if (isCorrect) {
                    correct++;
                    totalMarks += marksPerQuestion;
                } else {
                    wrong++;
                }
            }
        }

        double percentage = totalQ == 0 ? 0.0 : (totalMarks * 100.0 / (totalQ * marksPerQuestion));

        attempt.setCorrectCount(correct);
        attempt.setWrongCount(wrong);
        attempt.setUnattemptedCount(unattempted);
        attempt.setTotalMarks(totalMarks);
        attempt.setPercentage(percentage);
        attempt.setStatus(AttemptStatus.SUBMITTED);
        attempt.setEndTime(LocalDateTime.now());

        studentAttemptRepository.save(attempt);

        SubmitAttemptResponseDTO resp = new SubmitAttemptResponseDTO();
        resp.setAttemptId(attempt.getId());
        resp.setCorrect(correct);
        resp.setWrong(wrong);
        resp.setUnattempted(unattempted);
        resp.setTotalMarks(totalMarks);
        resp.setPercentage(percentage);

        return resp;
    }

    // -------------- GET RESULT --------------
    public AttemptResultDTO getResult(Long attemptId, Long studentId) {
    	System.out.println("attemptId :"+ attemptId + "studentId :"+studentId);
        StudentAttempt attempt = studentAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (!attempt.getStudent().getId().equals(studentId)) {
            throw new RuntimeException("Not allowed");
        }

        AttemptResultDTO dto = new AttemptResultDTO();
        dto.setAttemptId(attempt.getId());
        dto.setMockTestId(attempt.getMockTest().getId());
        dto.setMockTestTitle(attempt.getMockTest().getTitle());
        dto.setTotalQuestions(attempt.getTotalQuestions());
        dto.setCorrectCount(attempt.getCorrectCount());
        dto.setWrongCount(attempt.getWrongCount());
        dto.setUnattemptedCount(attempt.getUnattemptedCount());
        dto.setPercentage(attempt.getPercentage());
        dto.setTotalMarks(attempt.getTotalMarks());
        dto.setStatus(attempt.getStatus());
        dto.setStartTime(attempt.getStartTime());
        dto.setEndTime(attempt.getEndTime());
        return dto;
    }

    // -------------- ATTEMPT HISTORY --------------
    public List<AttemptResultDTO> getAttemptHistory(Long studentId) {
        List<StudentAttempt> list = studentAttemptRepository.findByStudentId(studentId);
        return list.stream().map(a -> {
            AttemptResultDTO dto = new AttemptResultDTO();
            dto.setAttemptId(a.getId());
            dto.setMockTestId(a.getMockTest().getId());
            dto.setMockTestTitle(a.getMockTest().getTitle());
            dto.setTotalQuestions(a.getTotalQuestions());
            dto.setCorrectCount(a.getCorrectCount());
            dto.setWrongCount(a.getWrongCount());
            dto.setUnattemptedCount(a.getUnattemptedCount());
            dto.setPercentage(a.getPercentage());
            dto.setTotalMarks(a.getTotalMarks());
            dto.setStatus(a.getStatus());
            dto.setStartTime(a.getStartTime());
            dto.setEndTime(a.getEndTime());
            return dto;
        }).collect(Collectors.toList());
    }

    // -------------- LEADERBOARD --------------
//    public List<LeaderboardEntryDTO> getLeaderboard(Long mockTestId, int limit) {
//        // fetch submitted attempts ordered by totalMarks desc
//        List<StudentAttempt> attempts = studentAttemptRepository.findByMockTestIdOrderByTotalMarksDesc(mockTestId);
//        return attempts.stream()
//                .filter(a -> a.getStatus() == AttemptStatus.SUBMITTED)
//                .limit(limit)
//                .map(a -> {
//                    LeaderboardEntryDTO ld = new LeaderboardEntryDTO();
//                    ld.setStudentId(a.getStudent().getId());
//                    ld.setStudentName(a.getStudent().getFullName());
//                    ld.setTotalMarks(a.getTotalMarks());
//                    ld.setPercentage(a.getPercentage());
//                    return ld;
//                }).collect(Collectors.toList());
//    }

    public List<LeaderboardEntryDTO> getLeaderboard(Long mockTestId, int limit) {

        List<StudentAttempt> attempts =
                studentAttemptRepository.findByMockTestIdOrderByTotalMarksDesc(mockTestId);
        Map<Long, StudentAttempt> bestAttemptMap = new LinkedHashMap<>();

        for (StudentAttempt attempt : attempts) {
            if (attempt.getStatus() != AttemptStatus.SUBMITTED) continue;

            Long studentId = attempt.getStudent().getId();
            if (!bestAttemptMap.containsKey(studentId)) {
                bestAttemptMap.put(studentId, attempt);
            }
        }

        // Convert to DTO
        return bestAttemptMap.values().stream()
                .limit(limit)
                .map(a -> {
                    LeaderboardEntryDTO ld = new LeaderboardEntryDTO();
                    ld.setStudentId(a.getStudent().getId());
                    ld.setStudentName(a.getStudent().getFullName());
                    ld.setTotalMarks(a.getTotalMarks());
                    ld.setPercentage(a.getPercentage());
                    return ld;
                })
                .collect(Collectors.toList());
    }

    
    
    // -------------- SUBJECT-WISE ANALYTICS --------------
    public List<SubjectAnalyticsDTO> getSubjectAnalytics(Long attemptId, Long studentId) {

        // verify attempt belongs to student
        StudentAttempt attempt = studentAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
        if (!attempt.getStudent().getId().equals(studentId)) throw new RuntimeException("Not allowed");

        // get questions and attempt answers
        List<AttemptQuestion> answers = attemptQuestionRepository.findByAttemptId(attemptId);
        Map<Long, AttemptQuestion> answerMap = answers.stream()
                .collect(Collectors.toMap(a -> a.getQuestion().getId(), a -> a));

        Map<String, List<Question>> bySubject = attempt.getMockTest().getQuestions().stream()
                .collect(Collectors.groupingBy(q -> q.getSubject() == null ? "GENERAL" : q.getSubject()));

        List<SubjectAnalyticsDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<Question>> e : bySubject.entrySet()) {
            String subject = e.getKey();
            int total = e.getValue().size();
            int correct = 0;
            int wrong = 0;

            for (Question q : e.getValue()) {
                AttemptQuestion aq = answerMap.get(q.getId());
                if (aq == null || aq.getSelectedOptionIds() == null || aq.getSelectedOptionIds().isBlank()) {
                    // unattempted counts into wrong? keep separate if you want
                } else {
                    Set<Long> userSelected = Arrays.stream(aq.getSelectedOptionIds().split(","))
                            .filter(s -> !s.isBlank())
                            .map(Long::valueOf)
                            .collect(Collectors.toSet());
                    Set<Long> correctOptionIds = q.getOptions().stream()
                            .filter(Option::getIsCorrect)
                            .map(Option::getId)
                            .collect(Collectors.toSet());
                    if (userSelected.equals(correctOptionIds)) correct++;
                    else wrong++;
                }
            }

            SubjectAnalyticsDTO dto = new SubjectAnalyticsDTO();
            dto.setSubject(subject);
            dto.setTotalQuestions(total);
            dto.setCorrect(correct);
            dto.setWrong(wrong);
            dto.setAccuracy(total == 0 ? 0.0 : (correct * 100.0 / total));
            result.add(dto);
        }

        return result;
    }

    // -------------- QUESTION-WISE SOLUTIONS --------------
    public List<QuestionSolutionDTO> getQuestionWiseSolutions(Long attemptId, Long studentId) {
        StudentAttempt attempt = studentAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
        if (!attempt.getStudent().getId().equals(studentId)) throw new RuntimeException("Not allowed");

        List<AttemptQuestion> answers = attemptQuestionRepository.findByAttemptId(attemptId);

        Map<Long, AttemptQuestion> answerMap = answers.stream()
                .collect(Collectors.toMap(a -> a.getQuestion().getId(), a -> a));

        List<QuestionSolutionDTO> out = new ArrayList<>();

        for (Question q : attempt.getMockTest().getQuestions()) {
            QuestionSolutionDTO qs = new QuestionSolutionDTO();
            qs.setQuestionId(q.getId());
            qs.setQuestionText(q.getQuestionText());
            List<OptionForAttemptDTO> opts = q.getOptions().stream()
                    .map(o -> {
                        OptionForAttemptDTO od = new OptionForAttemptDTO();
                        od.setOptionId(o.getId());
                        od.setOptionKey(o.getOptionKey());
                        od.setOptionText(o.getOptionText());
                        return od;
                    }).collect(Collectors.toList());
            qs.setOptions(opts);

            Set<Long> correctOptionIds = q.getOptions().stream()
                    .filter(Option::getIsCorrect)
                    .map(Option::getId)
                    .collect(Collectors.toSet());
            qs.setCorrectOptionIds(new ArrayList<>(correctOptionIds));

            AttemptQuestion a = answerMap.get(q.getId());
            if (a != null && a.getSelectedOptionIds() != null && !a.getSelectedOptionIds().isBlank()) {
                List<Long> userSel = Arrays.stream(a.getSelectedOptionIds().split(","))
                        .filter(s -> !s.isBlank())
                        .map(Long::valueOf).collect(Collectors.toList());
                qs.setUserSelectedOptionIds(userSel);
                qs.setIsCorrect(Boolean.TRUE.equals(a.getIsCorrect()));
            } else {
                qs.setUserSelectedOptionIds(Collections.emptyList());
                qs.setIsCorrect(false);
            }

            // explanation not implemented; if you have question.explanation set it
            qs.setExplanation(null);
            out.add(qs);
        }

        return out;
    }
}
