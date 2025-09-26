package com.example.demo.domain.profiles;

import com.example.demo.domain.Educations.Education;
import com.example.demo.domain.Experiences.Experience;
import com.example.demo.domain.Skills.Skill;
import com.example.demo.domain.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "profiles")
@NoArgsConstructor
@Getter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "company")
    private String company;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @Column(name = "github_username")
    private String githubUsername;

    @Column(name = "bio")
    private String bio;

    @Builder
    public Profile(Long id, String company, User user, List<Skill> skills, String githubUsername, String bio){
        this.id = id;
        this.company = company;
        this.user = user;
        this.skills = skills;
        this.githubUsername = githubUsername;
        this.bio = bio;
    }
}
