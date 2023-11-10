package com.huseynsharif.goizz.entities.concretes.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDTO {

    private String title;
    private String correctAnswers;
    private int quizId;

}
