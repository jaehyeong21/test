package com.example.demo.domain.users;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Builder
    public User(Long id, String name, String userId, String email, String password, LocalDate createdAt){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }
}
