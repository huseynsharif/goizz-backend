package com.huseynsharif.goizz.entities.concretes.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestorePasswordRequestDTO {

    private int userId;
    private String token;
    private String password;
    private String cpassword;

}
