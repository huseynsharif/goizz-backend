package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.DataResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.Question;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuestionDTO;

import java.util.List;

public interface QuestionService {

    Result add(CreateQuestionDTO createQuestionDTO);

    DataResult<List<Question>> getAllByQuiz_Id(int quizId);



}
