package com.huseynsharif.goizz.api.controller;

import com.huseynsharif.goizz.business.abstracts.QuestionService;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateQuestionDTO createQuestionDTO){
        return ResponseEntity.ok(this.questionService.add(createQuestionDTO));
    }



}
