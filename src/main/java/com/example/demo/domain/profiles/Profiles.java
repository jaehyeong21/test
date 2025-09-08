package com.example.demo.domain.profiles;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "profiles")
@NoArgsConstructor
@Getter
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "company")
    private String company;
    @Column(name = "skill")
    private String skill;
    @Column(name = "github_username")
    private String github_username;
    @Column(name = "bio")
    private String bio;

    @Builder
    public Profiles(Long id, String company, String skill, String github_username, String bio){
        this.id = id;
        this.company = company;
        this.skill = skill;
        this.github_username = github_username;
        this.bio = bio;
    }
}
