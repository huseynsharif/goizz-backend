package com.huseynsharif.goizz.entities.concretes.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuizDTO {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;


    @NotNull
    @NotBlank
    private int userId;

}