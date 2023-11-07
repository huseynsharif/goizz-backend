package com.huseynsharif.goizz.dataAccess.abstracts;


import com.huseynsharif.goizz.entities.concretes.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDAO extends JpaRepository<Question, Integer> {

    List<Question> getAllByQuiz_Id(int quizId);

}
