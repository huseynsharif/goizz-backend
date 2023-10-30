package com.huseynsharif.goizz.dataAccess.abstracts;

import com.huseynsharif.goizz.entities.concretes.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectAnswerDAO extends JpaRepository<CorrectAnswer, Integer> {



}
