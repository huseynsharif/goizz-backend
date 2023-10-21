package com.huseynsharif.goizz.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;


    @Column(name = "username",
            nullable = false,
            unique = true)
    private String username;

    @Column(name = "email",
            nullable = false,
            unique = true)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Column(name = "verified",
            nullable = false)
    private boolean verified;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
