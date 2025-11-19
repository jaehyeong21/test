package com.example.demo.service;

import com.example.demo.domain.Skills.Skill;
import com.example.demo.domain.profiles.Profile;
import com.example.demo.domain.users.User;
import com.example.demo.dto.ProfileDetailDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProfileDto> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();

        for (Profile profile : profiles) {
            profileDtos.add(ProfileDto.builder()
                    .bio(profile.getBio())
                    .company(profile.getCompany())
                    .skills(profile.getSkills())
                    .build()
            );
        }

        return profileDtos;
    }

    @Transactional(readOnly = true)
    public ProfileDto getProfileById(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () -> new RuntimeException("Not Profile")
        );

        ProfileDto profileDto = ProfileDto.builder()
                .name(profile.getUser().getName())
                .userId(profile.getUser().getUserId())
                .bio(profile.getBio())
                .company(profile.getCompany())
                .skills(profile.getSkills())
                .githubUsername(profile.getGithubUsername())
                .build();

        return profileDto;
    }
}
