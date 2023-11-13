package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.DataResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuizDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.QuizResponseDTO;

import java.util.List;

public interface QuizService {

    Result addNewQuiz(CreateQuizDTO createQuizDTO);

    DataResult<List<QuizResponseDTO>> getAllByUserId(int userId);

    DataResult<QuizResponseDTO> getById(int id);

}
