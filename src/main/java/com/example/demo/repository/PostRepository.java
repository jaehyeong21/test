package com.example.demo.repository;

import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findAllByUser(User user);
}
