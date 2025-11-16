package com.example.demo.domain.users;

import com.example.demo.domain.Comments.Comment;
import com.example.demo.domain.Posts.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Builder
    public User(Long id, String name, String userId, String email, String password, LocalDate createdAt){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.createdAt = createdAt;
    }

    public void addPost(Post post){
        posts.add(post);
        post.setUser(this);
    }

    public void encodePassword(String rawPassword){
        this.password = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String rawPassword) {
        return BCrypt.checkpw(rawPassword, this.password);
    }
}
