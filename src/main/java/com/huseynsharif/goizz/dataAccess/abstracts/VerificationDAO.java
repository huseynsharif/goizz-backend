package com.huseynsharif.goizz.dataAccess.abstracts;

import com.huseynsharif.goizz.entities.concretes.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationDAO extends JpaRepository<Verification, Integer> {

    Verification findVerificationByUser_Id(int id);

}
