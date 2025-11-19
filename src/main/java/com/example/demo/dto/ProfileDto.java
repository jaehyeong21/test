package com.example.demo.dto;

import com.example.demo.domain.Skills.Skill;
import com.example.demo.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String name;
    private String userId;
    private String bio;
    private String company;
    private String website;
    private String githubUsername;
    private String education;
    private String experiences;
    private String location;
    private List<Skill> skills;
}
