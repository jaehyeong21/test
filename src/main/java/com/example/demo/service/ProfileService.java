package com.example.demo.service;

import com.example.demo.domain.profiles.Profiles;
import com.example.demo.dto.ProfileDto;
import com.example.demo.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    // 목록 조회
    public List<ProfileDto> getProfiles() {
        List<Profiles> profiles = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();

        for (Profiles profile : profiles) {
            profileDtos.add(ProfileDto.builder()
                    .bio(profile.getBio())
                    .company(profile.getCompany())
                    .skills(Arrays.asList(profile.getSkill().split(",")))
                    .build()
            );
        }

        return profileDtos;
    }

    public Profiles getProfileById(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found :" + id));
    }
}
