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
public class ProfileDetailDto {
    private User user;
    private String bio;
    private String company;
    private String website;
    private String location;
    private List<Skill> skills;
    private String githubusername;
    private String educastions;
    private String experiences;
}
