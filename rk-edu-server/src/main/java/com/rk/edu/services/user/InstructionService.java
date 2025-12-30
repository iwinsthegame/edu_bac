package com.rk.edu.services.user;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rk.edu.dto.request.CreateInstructionRequestDTO;
import com.rk.edu.dto.request.InstructionCategoryDTO;
import com.rk.edu.dto.request.InstructionPointDTO;
import com.rk.edu.dto.request.InstructionSectionDTO;
import com.rk.edu.dto.request.InstructionSymbolDTO;
import com.rk.edu.dto.response.TestInstructionResponseDTO;
import com.rk.edu.model.InstructionCategory;
import com.rk.edu.model.InstructionPoint;
import com.rk.edu.model.InstructionSection;
import com.rk.edu.model.InstructionSymbol;
import com.rk.edu.model.MockTest;
import com.rk.edu.model.MockTestInstruction;
import com.rk.edu.repositories.*;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructionService {

    private final TestInstructionRepository instructionRepo;
    private final MockTestRepository mockTestRepository;

    @Transactional
    public MockTestInstruction createInstruction(CreateInstructionRequestDTO dto) {

        MockTest mock = mockTestRepository.findById(dto.getMockTestId())
                .orElseThrow(() -> new RuntimeException("MockTest not found"));

        MockTestInstruction ins = MockTestInstruction.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .mockTest(mock)
                .build();

        // Add sections
        List<InstructionSection> sections = dto.getSections().stream()
                .map(s -> InstructionSection.builder()
                        .name(s.getName())
                        .totalQuestions(s.getTotalQuestions())
                        .maxScore(s.getMaxScore())
                        .correctMarks(s.getCorrectMarks())
                        .negativeMarks(s.getNegativeMarks())
                        .instruction(ins)
                        .build())
                .toList();

        ins.setSections(sections);

        // Add categories + points
        List<InstructionCategory> categories = dto.getCategories().stream()
                .map(c -> {
                    InstructionCategory cat = InstructionCategory.builder()
                            .title(c.getTitle())
                            .displayOrder(c.getDisplayOrder())
                            .instruction(ins)
                            .build();

                    List<InstructionPoint> pts = c.getPoints().stream()
                            .map(p -> InstructionPoint.builder()
                                    .text(p.getText())
                                    .displayOrder(p.getDisplayOrder())
                                    .category(cat)
                                    .build())
                            .toList();

                    cat.setPoints(pts);
                    return cat;
                }).toList();

        ins.setCategories(categories);

        // Add symbols
        List<InstructionSymbol> symbols = dto.getSymbols().stream()
                .map(s -> InstructionSymbol.builder()
                        .label(s.getLabel())
                        .description(s.getDescription())
                        .instruction(ins)
                        .build())
                .toList();

        ins.setSymbols(symbols);

        return instructionRepo.save(ins);
    }

    public TestInstructionResponseDTO getInstruction(Long mockTestId) {

        MockTestInstruction ins = instructionRepo.findByMockTestId(mockTestId)
                .orElseThrow(() -> new RuntimeException("Instruction not found"));

        TestInstructionResponseDTO resp = new TestInstructionResponseDTO();
        resp.setTitle(ins.getTitle());
        resp.setDescription(ins.getDescription());

        // convert sections
        resp.setSections(
                ins.getSections().stream()
                        .map(s -> {
                            InstructionSectionDTO d = new InstructionSectionDTO();
                            d.setName(s.getName());
                            d.setTotalQuestions(s.getTotalQuestions());
                            d.setMaxScore(s.getMaxScore());
                            d.setCorrectMarks(s.getCorrectMarks());
                            d.setNegativeMarks(s.getNegativeMarks());
                            return d;
                        }).toList()
        );

        // convert categories
        resp.setCategories(
                ins.getCategories().stream()
                        .sorted(Comparator.comparing(InstructionCategory::getDisplayOrder))
                        .map(c -> {
                            InstructionCategoryDTO cd = new InstructionCategoryDTO();
                            cd.setTitle(c.getTitle());
                            cd.setDisplayOrder(c.getDisplayOrder());
                            cd.setPoints(
                                    c.getPoints().stream()
                                            .sorted(Comparator.comparing(InstructionPoint::getDisplayOrder))
                                            .map(p -> {
                                                InstructionPointDTO pd = new InstructionPointDTO();
                                                pd.setText(p.getText());
                                                pd.setDisplayOrder(p.getDisplayOrder());
                                                return pd;
                                            }).toList()
                            );
                            return cd;
                        }).toList()
        );

        // symbols
        resp.setSymbols(
                ins.getSymbols().stream()
                        .map(sym -> {
                            InstructionSymbolDTO sd = new InstructionSymbolDTO();
                            sd.setLabel(sym.getLabel());
                            sd.setDescription(sym.getDescription());
                            return sd;
                        }).toList()
        );

        return resp;
    }
}

