package com.huseynsharif.goizz.business.concretes;

import com.huseynsharif.goizz.business.abstracts.QuestionService;
import com.huseynsharif.goizz.core.utilities.results.ErrorResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.dataAccess.abstracts.CorrectAnswerDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuestionDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuizDAO;
import com.huseynsharif.goizz.entities.concretes.Question;
import com.huseynsharif.goizz.entities.concretes.Quiz;
import com.huseynsharif.goizz.entities.concretes.dtos.CreateQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class QuestionManager implements QuestionService {

    private final QuestionDAO questionDAO;
    private final QuizDAO quizDAO;
    private final CorrectAnswerDAO correctAnswerDAO;

    @Override
    public Result add(CreateQuestionDTO createQuestionDTO) {

        Quiz quiz = this.quizDAO.findById(createQuestionDTO.getQuizId()).orElse(null);

        if (quiz==null){
            return new ErrorResult("Cannot find quiz with given quizId: " + createQuestionDTO.getQuizId());
        }

        Question question = new Question(
                createQuestionDTO.getTitle(),
                quiz
        );

        // TODO: correct answers

        return null;
    }
}
