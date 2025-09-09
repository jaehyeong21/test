package com.example.demo.controller;


import com.example.demo.domain.profiles.Profiles;
import com.example.demo.dto.ProfileDto;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/{id}")
    public Profiles getProfile(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }
}

