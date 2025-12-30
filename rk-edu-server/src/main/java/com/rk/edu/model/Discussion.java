package com.rk.edu.model;

import java.time.LocalDateTime;

import com.rk.edu.enums.EntityType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.*;




@Entity
@Table(name = "discussion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    private Long entityId;

    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Long parentId;

    @Column(nullable = false)
    private Integer upvotes = 0;

    @Column(nullable = false)
    private Boolean accepted = false;

    private LocalDateTime createdAt;

    // ✅ GUARANTEE defaults at DB insert time
    @PrePersist
    public void prePersist() {
    	if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    	
        if (upvotes == null) upvotes = 0;
        if (accepted == null) accepted = false;
    }
}
