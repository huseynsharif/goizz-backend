package com.huseynsharif.goizz.business.concretes;

import com.huseynsharif.goizz.business.abstracts.QuizService;
import com.huseynsharif.goizz.core.utilities.results.ErrorResult;
import com.huseynsharif.goizz.core.utilities.results.Result;
import com.huseynsharif.goizz.core.utilities.results.SuccessResult;
import com.huseynsharif.goizz.dataAccess.abstracts.QuizDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.UserDAO;
import com.huseynsharif.goizz.entities.concretes.Quiz;
import com.huseynsharif.goizz.entities.concretes.User;
import com.huseynsharif.goizz.entities.concretes.dtos.CreateQuizDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizManager implements QuizService {

    private final QuizDAO quizDAO;
    private final UserDAO userDAO;


    @Override
    public Result addNewQuiz(CreateQuizDTO createQuizDTO) {

        User user = this.userDAO.findById(createQuizDTO.getUserId()).orElse(null);
        if (user == null) {
            return new ErrorResult("Cannot find user with given id: " + createQuizDTO.getUserId());
        }

        Quiz quiz = new Quiz(
                createQuizDTO.getTitle(),
                user
        );
        this.quizDAO.save(quiz);
        return new SuccessResult("Quiz was saved successfully");
    }
}
