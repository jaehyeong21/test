package com.example.demo.service;

import com.example.demo.domain.Likes.Like;
import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean toggleLike(Long userId, Long postId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저없음"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("post 없음"));

        Optional<Like> existing = likeRepository.findByUserAndPost(user,post);

        if(existing.isPresent()){
            likeRepository.delete(existing.get());
            return false;
        }

        Like like = new Like();
        like.setUser(user);
        post.addLike(like);

        likeRepository.save(like);

        return true;
    }
}
