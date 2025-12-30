package com.rk.edu.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "test_series")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_subcategory_id", nullable = false)
    @JsonBackReference
    private ExamSubCategory examSubCategory;
    
    
    @OneToMany(mappedBy = "testSeries", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MockTest> mockTests;
    private Long createdByAdminId;
    
}
    
    
    
  /*
    
    
    private String subtitle;

    @Column(length = 1000)
    private String description;

    private String thumbnailUrl;

    

    @ElementCollection
    @CollectionTable(name = "test_series_tags", joinColumns = @JoinColumn(name = "test_series_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "test_series_languages", joinColumns = @JoinColumn(name = "test_series_id"))
    @Column(name = "language")
    private Set<String> languages;

    

    private Boolean isPaid;
    private Double price;
    private Double discountedPrice;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer validityDays;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    private Boolean isPublished;
    private Boolean isActive;

    private Integer sortOrder;
    private Boolean isFeatured;
    private Boolean isTrending;
    private String bannerUrl;
    private String highlightText;

    @Column(length = 300)
    private String seoTitle;

    @Column(length = 500)
    private String seoDescription;

    private String slug;

    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    private Integer totalMockTests;
    private Integer totalQuestions;
    private Long enrolledCount;
    private Long purchaseCount;
    private Double averageRating;
    private Long totalRatings;

    private Integer version;
    private Boolean requiresUpdate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Long createdByAdminId;
    */

