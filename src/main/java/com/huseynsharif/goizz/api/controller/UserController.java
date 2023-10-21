package com.huseynsharif.goizz.api.controller;

import com.huseynsharif.goizz.business.abstracts.UserService;
import com.huseynsharif.goizz.core.utilities.results.ErrorDataResult;
import com.huseynsharif.goizz.entities.concretes.dtos.LoginRequestDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.SignUpRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(this.userService.signUp(signUpRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(this.userService.logIn(loginRequestDTO));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){

        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {

            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());

        }

        ErrorDataResult<Object> errors = new ErrorDataResult<>(validationErrors, "Validation errors.");

        return errors;

    }



}
