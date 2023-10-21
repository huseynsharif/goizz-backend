package com.huseynsharif.goizz.business.concretes;

import com.huseynsharif.goizz.business.abstracts.UserService;
import com.huseynsharif.goizz.core.utilities.emailSendings.abstracts.EmailService;
import com.huseynsharif.goizz.core.utilities.results.*;
import com.huseynsharif.goizz.dataAccess.abstracts.UserDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.VerificationDAO;
import com.huseynsharif.goizz.entities.concretes.User;
import com.huseynsharif.goizz.entities.concretes.Verification;
import com.huseynsharif.goizz.entities.concretes.dtos.LoginRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.SignUpRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.UserLoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserDAO userDAO;
    private final EmailService emailService;
    private final VerificationDAO verificationDAO;

    @Value("$huseynsharif.frontend.goizz.host")
    private String host;
    @Value("$huseynsharif.frontend.goizz.port")
    private String port;

    @Override
    public Result signUp(SignUpRequestDTO signUpRequestDTO) {

        if (!Objects.equals(signUpRequestDTO.getPassword(), signUpRequestDTO.getCpassword())) {
            return new ErrorDataResult<>("Passwords must be same.");
        }

        if (this.userDAO.existsUserByUsername(signUpRequestDTO.getUsername())) {
            return new ErrorDataResult<>("Username was already taken.");
        }

        if (this.userDAO.existsUserByEmail(signUpRequestDTO.getEmail())) {
            return new ErrorDataResult<>("Email was already taken.");
        }

        User user = new User(
                signUpRequestDTO.getUsername(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getPassword()
        );

        this.userDAO.save(user);

        // Email Verification
        Verification emailVerification = new Verification(user);
        this.verificationDAO.save(emailVerification);
        this.emailService.sendVerificationEmailHtml(
                user.getUsername(),
                user.getEmail(),
                verificationLinkGenerator(
                        user.getId(),
                        emailVerification.getToken()
                )
        );

        return new SuccessResult("User was successfully saved.");
    }

    private String verificationLinkGenerator(int userId, String token) {

        // TODO: frontu yazandan sonra qayıt bura
        return "http://localhost:3000/verificate-user-with-link/" + userId + "/" + token;
    }

    @Override
    public DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO) {

        User user = this.userDAO.findUserByEmailAndPassword(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        if (user == null) {
            return new ErrorDataResult<>("Email or password is incorrect");
        }
        if (!user.isVerified()) {
            return new ErrorDataResult<>("User has not verified yet. Please, verify your account");
        }

        UserLoginResponseDTO response = new UserLoginResponseDTO(
                user.getId(), user.getUsername()
        );

        return new SuccessDataResult<>(response, "User successfully finded.");

    }
}
