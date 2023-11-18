package com.huseynsharif.goizz.business.concretes;

import com.huseynsharif.goizz.business.abstracts.QuizService;
import com.huseynsharif.goizz.core.utilities.results.*;
import com.huseynsharif.goizz.dataAccess.abstracts.CorrectAnswerDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuestionDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.QuizDAO;
import com.huseynsharif.goizz.dataAccess.abstracts.UserDAO;
import com.huseynsharif.goizz.entities.concretes.CorrectAnswer;
import com.huseynsharif.goizz.entities.concretes.Question;
import com.huseynsharif.goizz.entities.concretes.Quiz;
import com.huseynsharif.goizz.entities.concretes.User;
import com.huseynsharif.goizz.entities.concretes.dtos.request.CreateQuizDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.request.UserAnswerDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.QuestionAnswerDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.QuestionDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.QuizQuestionDTO;
import com.huseynsharif.goizz.entities.concretes.dtos.response.QuizResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuizManager implements QuizService {

    private final QuizDAO quizDAO;
    private final UserDAO userDAO;
    private final QuestionDAO questionDAO;
    private final CorrectAnswerDAO correctAnswerDAO;

    private final SimpMessagingTemplate simpMessagingTemplate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                           
    @Override
    public Result addNewQuiz(CreateQuizDTO createQuizDTO) {
        User user = this.userDAO.findById(createQuizDTO.getUserId()).orElse(null);
        if (user == null) {
            return new ErrorResult("Cannot find user with given id: " + createQuizDTO.getUserId());
        }

        Quiz quiz = new Quiz(
                createQuizDTO.getTitle(),
                createQuizDTO.getDescription(),
                user
        );
        this.quizDAO.save(quiz);
        return new SuccessDataResult<Integer>(quiz.getId(),"Quiz was saved successfully");
    }

    @Override
    public DataResult<List<QuizResponseDTO>> getAllByUserId(int userId) {

        List<Quiz> quizzes = this.quizDAO.findAllByCreatedBy_Id(userId);
        if (quizzes.isEmpty()) {
            return new ErrorDataResult<>("Cannot find quiz with given userId: "+userId);
        }

        List<QuizResponseDTO> response = quizzes
                .stream()
                .map(
                        (quiz)-> new QuizResponseDTO(
                                    quiz.getId(),
                                    quiz.getTitle(),
                                    quiz.getDescription(),
                                    quiz.getCreatedAt().format(formatter)
                                )
                ).toList();
        return new SuccessDataResult<>(response, "All quizzes for: " + userId);
    }

    @Override
    public DataResult<QuizQuestionDTO> getById(int id) {

        Quiz quiz = this.quizDAO.findById(id).orElse(null);
        if (quiz == null) {
            return new ErrorDataResult<>("Cannot find quiz with given quizId: " + id);
        }

        List<Question> questions = this.questionDAO.getAllByQuiz_Id(id);
        if (questions.isEmpty()){
            return new ErrorDataResult<>("Cannot find question with given id: " + id);
        }

        List<QuestionAnswerDTO> questionAnswerDTOS = questions.stream().map(
                (question)-> new QuestionAnswerDTO(
                        new QuestionDTO(question.getId(), question.getTitle()),
                        this.correctAnswerDAO.findCorrectAnswerByAnswerTo_Id(question.getId()).getAnswer())
        ).toList();

        QuizResponseDTO quizResponseDTO = new QuizResponseDTO(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCreatedAt().format(formatter)
        );

        QuizQuestionDTO response = new QuizQuestionDTO(quizResponseDTO, questionAnswerDTOS);

        return new SuccessDataResult<>(response, "Successfully found.");
    }

    @Override
    public Result sendQuestion(int questionId) {

        Question question = this.questionDAO.findById(questionId).orElse(null);

        if (question == null) {
            return new ErrorResult("Cannot find question with given questionId: " + questionId);
        }

        QuestionDTO response = new QuestionDTO(
                question.getId(),
                question.getTitle()
        );
        simpMessagingTemplate.convertAndSend("/topic/rt-quiz-client/" + question.getQuiz().getId(), response);
        return new SuccessResult("Successfully sent.");
    }

    @Override
    public Result receiveAnswer(UserAnswerDTO userAnswerDTO) {

        if (userAnswerDTO.getAnswer() == null) {
            return null;
        }

        CorrectAnswer correctAnswer = this.correctAnswerDAO.findCorrectAnswerByAnswerTo_Id(userAnswerDTO.getQuestionId());
        if (correctAnswer == null) {
            return new ErrorResult("Cannot find correct answer with given question id: " + userAnswerDTO.getQuestionId());
        }

        if (correctAnswer.getAnswer().trim().equalsIgnoreCase(userAnswerDTO.getAnswer().trim())){

            System.out.println("DUZ TAPDI KIMSE");
            User user = this.userDAO.findById(userAnswerDTO.getUserId()).orElse(null);
            if (user == null) {
                return new ErrorResult("Cannot find user with given userId: "+userAnswerDTO.getUserId());
            }

            simpMessagingTemplate.convertAndSend("/topic/rt-quiz-correct-answerers/"+userAnswerDTO.getQuestionId(), user.getUsername());
        }

        return null;
    }

}
