package com.huseynsharif.goizz.entities.concretes.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAnswerDTO {

    private int questionId;
    private int userId;
    private String answer;

}
