package com.example.demo.service;

import com.example.demo.domain.Skills.Skill;
import com.example.demo.domain.profiles.Profile;
import com.example.demo.domain.users.User;
import com.example.demo.dto.ProfileDetailDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<ProfileDto> getProfiles() {
        List<Profile> Profiles = profileRepository.findAll();
        List<ProfileDto> ProfileDtos = new ArrayList<>();

        for (Profile profile : Profiles) {
            ProfileDtos.add(ProfileDto.builder()
                    .bio(profile.getBio())
                    .company(profile.getCompany())
                    .skills(profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList()))
                    .build()
            );
        }

        return ProfileDtos;
    }

    @Transactional
    public ProfileDetailDto getProfileById(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new RuntimeException("Not Profile")
        );

        ProfileDetailDto profileDetailDto = ProfileDetailDto.builder()
                .user(profile.getUser())
                .bio(profile.getBio())
                .company(profile.getCompany())
                .skills(profile.getSkills())
                .githubUsername(profile.getGithubUsername())
                .build();
        return profileDetailDto;
    }
}
