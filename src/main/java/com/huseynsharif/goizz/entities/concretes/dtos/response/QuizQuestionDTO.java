package com.huseynsharif.goizz.entities.concretes.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizQuestionDTO {

    private QuizResponseDTO quiz;
    private List<QuestionAnswerDTO> questions;

}
