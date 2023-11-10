package com.huseynsharif.goizz.business.concretes;

import com.huseynsharif.goizz.business.abstracts.QuestionService;
import com.huseynsharif.goizz.core.utilities.results.*;
import com.huseynsharif.goizz.dataAccess.abstracts.CorrectAnswerDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuestionDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuizDAO;
import com.huseynsharif.goizz.entities.concretes.CorrectAnswer;
import com.huseynsharif.goizz.entities.concretes.Question;
import com.huseynsharif.goizz.entities.concretes.Quiz;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor
public class QuestionManager implements QuestionService {

    private final QuestionDAO questionDAO;
    private final QuizDAO quizDAO;
    private final CorrectAnswerDAO correctAnswerDAO;

    @Override
    public Result add(CreateQuestionDTO createQuestionDTO) {

//        if (createQuestionDTO != null) {
//            System.out.println(createQuestionDTO);
//            return null;
//        }

        Quiz quiz = this.quizDAO.findById(createQuestionDTO.getQuizId()).orElse(null);

        if (quiz==null){
            return new ErrorResult("Cannot find quiz with given quizId: " + createQuestionDTO.getQuizId());
        }

        Question question = new Question(
                createQuestionDTO.getTitle(),
                quiz
        );
        this.questionDAO.save(question);

//        for (String answer : createQuestionDTO.getCorrectAnswers()) {
//            CorrectAnswer correctAnswer = new CorrectAnswer(
//                answer, question
//            );
//            this.correctAnswerDAO.save(correctAnswer);
//        }

        // correct answer 1den cox et
        this.correctAnswerDAO.save(new CorrectAnswer(createQuestionDTO.getCorrectAnswers(), question));


        return new SuccessResult("Successfully saved");
    }

    @Override
    public DataResult<List<Question>> getAllByQuiz_Id(int quizId) {

        List<Question> questions = this.questionDAO.getAllByQuiz_Id(quizId);
        if (questions.isEmpty()){
            return new ErrorDataResult<>("Cannot find question with given id: " + quizId);
        }


        return new SuccessDataResult<>(questions, "Successfully listed.");
    }
}
