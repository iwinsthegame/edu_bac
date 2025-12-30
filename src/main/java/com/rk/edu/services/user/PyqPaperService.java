package com.rk.edu.services.user;



import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rk.edu.dto.request.PyqPaperRequestDTO;
import com.rk.edu.dto.response.PyqPaperResponseDTO;
import com.rk.edu.model.PyqPaper;
import com.rk.edu.repositories.ExamCategoryRepository;
import com.rk.edu.repositories.ExamSubCategoryRepository;
import com.rk.edu.repositories.PyqPaperRepository;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PyqPaperService {

    private final PyqPaperRepository pyqRepo;
    private final ExamCategoryRepository examCategoryRepo;
    private final ExamSubCategoryRepository examSubCategoryRepo;

//    private static final String UPLOAD_DIR =
//            "/Users/rohitkumar/Documents/STSWorkSpace/edu-backend-poc/gitcode/pyqpdf/";
    
//    private final String UPLOAD_DIR = "uploads/pyq/";
    
    @Value("${file.upload.pyq-dir}")
    private String UPLOAD_DIR;
    
    


    public PyqPaperResponseDTO uploadPdf(
            MultipartFile file,
            PyqPaperRequestDTO dto
    ) throws Exception {

        // create folder if not exists
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        // generate unique filename
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;

        Files.write(new File(filePath).toPath(), file.getBytes());

        PyqPaper paper = new PyqPaper();
        paper.setTitle(dto.getTitle());
        paper.setYear(dto.getYear());
        paper.setExamName(dto.getExamName());
        paper.setPdfUrl("/" + filePath);
        paper.setExamStage(dto.getExamStage());
        

        paper.setExamCategory(
                examCategoryRepo.findById(dto.getExamCategoryId()).orElseThrow()
        );

        paper.setExamSubCategory(
                examSubCategoryRepo.findById(dto.getExamSubCategoryId()).orElseThrow()
        );

        PyqPaper saved = pyqRepo.save(paper);

        return mapToResponse(saved);
    }

    public List<PyqPaperResponseDTO> getAll() {
        return pyqRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    /* ---------------- GET BY CATEGORY ---------------- */

    public List<PyqPaperResponseDTO> getByCategory(Long categoryId) {
        return pyqRepo.findByExamCategory_Id(categoryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* ---------------- GET BY SUB CATEGORY ---------------- */

    public List<PyqPaperResponseDTO> getBySubCategory(Long subCategoryId) {
        return pyqRepo.findByExamSubCategory_Id(subCategoryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* ---------------- GET BY YEAR ---------------- */

    public List<PyqPaperResponseDTO> getByYear(Integer year) {
        return pyqRepo.findByYear(year)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* ---------------- GET BY CATEGORY + YEAR ---------------- */

    public List<PyqPaperResponseDTO> getByCategoryAndYear(Long categoryId, Integer year) {
        return pyqRepo.findByExamCategory_IdAndYear(categoryId, year)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /* ---------------- DELETE PYQ ---------------- */

    public void deletePyq(Long id) {
        PyqPaper pyq = pyqRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("PYQ not found"));
        pyqRepo.delete(pyq);
    }


    private PyqPaperResponseDTO mapToResponse(PyqPaper paper) {
        PyqPaperResponseDTO dto = new PyqPaperResponseDTO();
        dto.setId(paper.getId());
        dto.setTitle(paper.getTitle());
        dto.setYear(paper.getYear());
        dto.setExamName(paper.getExamName());
        dto.setPdfUrl(paper.getPdfUrl());
        dto.setExamStage(paper.getExamStage());
        dto.setExamCategory(paper.getExamCategory().getName());
        dto.setExamSubCategory(paper.getExamSubCategory().getName());
        return dto;
    }
}

