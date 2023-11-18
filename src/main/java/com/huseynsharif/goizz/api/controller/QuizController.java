package com.huseynsharif.goizz.api.controller;

import com.huseynsharif.goizz.business.abstracts.QuizService;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuizDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.request.UserAnswerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateQuizDTO createQuizDTO){
        return ResponseEntity.ok(this.quizService.addNewQuiz(createQuizDTO));
    }

    @GetMapping("/getall-by-userId")
    public ResponseEntity<?> getAll(@RequestParam int userId){
        return ResponseEntity.ok(this.quizService.getAllByUserId(userId));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getById(@RequestParam("quizId") int quizId){
        return ResponseEntity.ok(this.quizService.getById(quizId));
    }

    @MessageMapping("/rt-quiz")
    public int sendQuestion(@Payload int questionId){

        this.quizService.sendQuestion(questionId);
        return questionId;
    }

    @PostMapping("/receive-answer")
    public ResponseEntity<?> receiveAnswer(@RequestBody UserAnswerDTO userAnswerDTO){
        return ResponseEntity.ok(this.quizService.receiveAnswer(userAnswerDTO));
    }

}
