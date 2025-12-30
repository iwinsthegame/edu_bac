package com.rk.edu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "attempt_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptQuestion {

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "attempt_id")
 private StudentAttempt attempt;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "question_id")
 private Question question;

 
 private String selectedOptionIds;

 private Boolean isCorrect;
 private Long timeSpentSeconds;
}


