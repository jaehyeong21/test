package com.example.demo.repository;

import com.example.demo.domain.profiles.Profile;
import com.example.demo.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}
