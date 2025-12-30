package com.rk.edu.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mock_test")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MockTest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer durationMinutes;
    private Integer totalMarks;
    private Boolean isActive;
    private Integer totalQuestions;

    @ManyToOne
    @JoinColumn(name = "test_series_id")
    @JsonIgnore
    private TestSeries testSeries;

    @OneToMany(mappedBy = "mockTest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;
}

