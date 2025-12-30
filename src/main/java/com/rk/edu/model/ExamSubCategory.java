package com.rk.edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exam_sub_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamSubCategory {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "exam_category_id", nullable = false)
    private ExamCategory examCategory;
    
    @OneToMany(mappedBy = "examSubCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TestSeries> testSeriesList;


}
