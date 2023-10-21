package com.huseynsharif.goizz.dataAccess.abstracts;

import com.huseynsharif.goizz.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findUserByEmailAndPassword(String email, String password);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

}
