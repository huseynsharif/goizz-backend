package com.huseynsharif.goizz.api.controller;

import com.huseynsharif.goizz.business.abstracts.QuizService;
import com.huseynsharif.goizz.entities.concretes.dtos.CreateQuizDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quizs")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/add-new-quiz")
    public ResponseEntity<?> addNewQuiz(@RequestBody CreateQuizDTO createQuizDTO){
        return ResponseEntity.ok(this.quizService.addNewQuiz(createQuizDTO));
    }

}
