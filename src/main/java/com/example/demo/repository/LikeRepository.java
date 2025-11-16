package com.example.demo.repository;

import com.example.demo.domain.Likes.Like;
import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
}
