package com.example.demo.service;

import com.example.demo.domain.profiles.Profiles;
import com.example.demo.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    // 목록 조회
    public List<Profiles> getProfiles() {
        return profileRepository.findAll();
    }

    // 상세 조회
    public Profile getProfileById(Long id){
        return profileRepository.findById(id);
    }
}
