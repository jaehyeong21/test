package com.example.demo;

import com.example.demo.domain.profiles.Profile;
import com.example.demo.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class profileRepositoryTest {

	@Autowired
	private ProfileRepository profileRepository;

	@Test
	void testInsertAndSelect() {
		Profile profile = Profile.builder()
				.company("Busan Bank")
				.bio("백엔드 개발자")
				.build();

		profileRepository.save(profile);

		List<Profile> Profiles = profileRepository.findAll();
		for (Profile p : Profiles) {
			System.out.println(p.getId() + " / " + p.getCompany() + " / ");
		}
	}
}
