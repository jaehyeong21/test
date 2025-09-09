package com.example.demo;

import com.example.demo.domain.profiles.Profiles;
import com.example.demo.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProfileRepositoryTest {

	@Autowired
	private ProfileRepository profileRepository;

	@Test
	void testInsertAndSelect() {
		Profiles profile = Profiles.builder()
				.company("Busan Bank")
				.skill("Java, Spring Boot")
				.github_username("jaehyeong-dev")
				.bio("백엔드 개발자")
				.build();

		profileRepository.save(profile);

		List<Profiles> profiles = profileRepository.findAll();
		for (Profiles p : profiles) {
			System.out.println(p.getId() + " / " + p.getCompany() + " / " + p.getSkill());
		}
	}
}
