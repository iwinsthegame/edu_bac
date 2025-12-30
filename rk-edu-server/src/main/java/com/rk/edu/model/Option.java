package com.rk.edu.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 
 private String optionKey;

 @Column(nullable = false, length = 1000)
 private String optionText;

 
 private Boolean isCorrect = false;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "question_id")
 private Question question;
}


