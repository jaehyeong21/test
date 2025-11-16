package com.example.demo.domain.Comments;

import com.example.demo.domain.Posts.Post;
import com.example.demo.domain.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Builder
    public Comment(String content, User user, Post post) {
        this.content = content;
        this.createdAt = LocalDate.now();
        setUser(user);
        setPost(post);
    }

    public void setPost(Post post){
        this.post = post;

        if(!post.getComments().contains(this)){
            post.getComments().add(this);
        }
    }

    public void setUser(User user){
        this.user = user;

        if(!user.getComments().contains(this)){
            user.getComments().add(this);
        }
    }

}
