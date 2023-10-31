package com.huseynsharif.goizz.api.controller;

import com.huseynsharif.goizz.business.abstracts.QuizService;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuizDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateQuizDTO createQuizDTO){
        return ResponseEntity.ok(this.quizService.addNewQuiz(createQuizDTO));
    }

    @PostMapping("/getall-by-userId")
    public ResponseEntity<?> getAll(@RequestParam int userId){
        return ResponseEntity.ok(this.quizService.getAllByUserId(userId));
    }

}
