package com.huseynsharif.goizz.entities.concretes.dtos.response;

import com.huseynsharif.goizz.entities.concretes.CorrectAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDTO {

    private QuestionDTO question;
    private String correctAnswer;

}
