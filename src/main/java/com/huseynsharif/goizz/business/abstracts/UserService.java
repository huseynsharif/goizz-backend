package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.DataResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.dtos.LoginRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.RestorePasswordRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.SignUpRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.UserLoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Result signUp(SignUpRequestDTO signUpRequestDTO);

    DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO);

    Result verifyEmailWithLink(int userId, String token);

    Result sendForgotPasswordEmail(String email);

    Result restorePassword(RestorePasswordRequestDTO restoreRequest);

}
