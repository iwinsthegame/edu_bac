package com.rk.edu.controllers.user;


import com.rk.edu.dto.request.PyqPaperRequestDTO;
import com.rk.edu.dto.response.PyqPaperResponseDTO;
import com.rk.edu.services.user.PyqPaperService;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/rk/user/pyq")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PyqPaperController {

    private final PyqPaperService pyqService;
    private final ObjectMapper objectMapper;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<PyqPaperResponseDTO> uploadPyq(
            @RequestPart("file") MultipartFile file,
            @RequestPart("data") String data
    ) throws Exception {

        PyqPaperRequestDTO dto =
                objectMapper.readValue(data, PyqPaperRequestDTO.class);

        return ResponseEntity.ok(pyqService.uploadPdf(file, dto));
    }

    @GetMapping
    public ResponseEntity<List<PyqPaperResponseDTO>> getAll() {
        return ResponseEntity.ok(pyqService.getAll());
    }
    
    /* ---------------- GET BY EXAM CATEGORY ---------------- */

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PyqPaperResponseDTO>> getByCategory(
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(pyqService.getByCategory(categoryId));
    }

    /* ---------------- GET BY SUB CATEGORY ---------------- */

    @GetMapping("/subcategory/{subCategoryId}")
    public ResponseEntity<List<PyqPaperResponseDTO>> getBySubCategory(
            @PathVariable Long subCategoryId
    ) {
        return ResponseEntity.ok(pyqService.getBySubCategory(subCategoryId));
    }

    /* ---------------- GET BY YEAR ---------------- */

    @GetMapping("/year/{year}")
    public ResponseEntity<List<PyqPaperResponseDTO>> getByYear(
            @PathVariable Integer year
    ) {
        return ResponseEntity.ok(pyqService.getByYear(year));
    }

    /* ---------------- GET BY EXAM + YEAR ---------------- */

    @GetMapping("/category/{categoryId}/year/{year}")
    public ResponseEntity<List<PyqPaperResponseDTO>> getByCategoryAndYear(
            @PathVariable Long categoryId,
            @PathVariable Integer year
    ) {
        return ResponseEntity.ok(pyqService.getByCategoryAndYear(categoryId, year));
    }

    /* ---------------- DELETE PYQ ---------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePyq(@PathVariable Long id) {
        pyqService.deletePyq(id);
        return ResponseEntity.ok("PYQ deleted successfully");
    }
}

