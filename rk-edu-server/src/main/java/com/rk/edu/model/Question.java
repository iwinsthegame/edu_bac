package com.rk.edu.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.rk.edu.enums.QuestionType;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable = false, columnDefinition = "LONGTEXT")
 private String questionText;

 @Enumerated(EnumType.STRING)
 @Column(nullable = false)
 private QuestionType questionType;

 
 private String subject;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "mock_test_id")
 private MockTest mockTest;

 @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
 @Builder.Default
 private List<Option> options = new ArrayList<>();
}

