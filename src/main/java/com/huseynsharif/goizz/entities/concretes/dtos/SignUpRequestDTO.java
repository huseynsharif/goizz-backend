package com.huseynsharif.goizz.entities.concretes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    @NotBlank
    @NotNull
    private String username;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 100)
    private String cpassword;

}
