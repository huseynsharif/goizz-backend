package com.huseynsharif.goizz.dataAccess.abstracts;

import com.huseynsharif.goizz.entities.concretes.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {



}
