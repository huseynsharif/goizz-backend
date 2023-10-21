package com.huseynsharif.goizz.dataAccess.abstracts;


import com.huseynsharif.goizz.entities.concretes.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDAO extends JpaRepository<Question, Integer> {

}
