package com.example.demo.domain.Skills;

import com.example.demo.domain.profiles.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Table(name = "skills")
@Getter
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Skill(String name, Profile profile){
        this.name = name;
    }
}