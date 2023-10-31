package com.huseynsharif.goizz.business.abstracts;

import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuestionDTO;

public interface QuestionService {

    Result add(CreateQuestionDTO createQuestionDTO);


}
