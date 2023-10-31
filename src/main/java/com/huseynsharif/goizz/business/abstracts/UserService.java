package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.DataResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.dtos.request.LoginRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.request.RestorePasswordRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.request.SignUpRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.UserLoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Result signUp(SignUpRequestDTO signUpRequestDTO);

    DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO);

    Result verifyEmailWithLink(int userId, String token);

    Result sendForgotPasswordEmail(String email);

    Result restorePassword(RestorePasswordRequestDTO restoreRequest);

}
