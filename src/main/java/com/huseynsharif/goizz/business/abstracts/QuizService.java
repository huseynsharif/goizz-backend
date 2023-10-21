package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.DataResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.Quiz;
import com.huseynsharif.goizz.entities.concretes.dtos.CreateQuizDTO;

public interface QuizService {

    Result addNewQuiz(CreateQuizDTO createQuizDTO);

}
