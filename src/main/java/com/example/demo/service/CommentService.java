package com.example.demo.service;

import com.example.demo.domain.Comments.Comment;
import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import com.example.demo.dto.CommentRequestDto;
import com.example.demo.global.security.exception.BadRequestException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long userId, Long postId, CommentRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 유저"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 게시글"));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .user(user)
                .post(post)
                .build();

        commentRepository.save(comment);
    }

    public List<CommentRequestDto> getComments(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 게시물입니다."));

        List<Comment> comments = commentRepository.findAllByPost(post);
        List<CommentRequestDto> commentDto = new ArrayList<>();

        for (Comment comment : comments) {
            CommentRequestDto dto = CommentRequestDto.builder()
                    .content(comment.getContent())
                    .userId(comment.getUser().getUserId())
                    .username(comment.getUser().getName())
                    .build();

            commentDto.add(dto);
        }

        return commentDto;
    }

}
