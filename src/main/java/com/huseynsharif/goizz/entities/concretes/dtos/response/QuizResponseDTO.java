package com.huseynsharif.goizz.entities.concretes.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDTO {

    private int id;
    private String title;
    private String description;
    private String creationDate;

}
