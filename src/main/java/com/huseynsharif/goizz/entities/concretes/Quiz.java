package com.huseynsharif.goizz.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quizs")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int id;

    @Column(name = "title",
            nullable = false)
    private String title;

    @Column(name = "description",
            nullable = false)
    private String description;



    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private User createdBy;

    @CreationTimestamp
    @Column(name = "created_at",
            nullable = false)
    private LocalDateTime createdAt;

    public Quiz(String title, User createdBy) {
        this.title = title;
        this.createdBy = createdBy;
    }
}
