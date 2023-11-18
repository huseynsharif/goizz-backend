package com.huseynsharif.goizz.dataAccess.abstracts;

import com.huseynsharif.goizz.entities.concretes.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {

    List<Quiz> findAllByCreatedBy_Id(int userId);

}
