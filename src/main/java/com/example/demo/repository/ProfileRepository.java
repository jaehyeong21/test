package com.example.demo.repository;

import com.example.demo.domain.profiles.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profiles, Long> {

}
