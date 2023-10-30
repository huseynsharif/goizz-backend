package com.huseynsharif.goizz.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(name = "title",
            nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quizId;

    public Question(String title, Quiz quizId) {
        this.title = title;
        this.quizId = quizId;
    }
}
