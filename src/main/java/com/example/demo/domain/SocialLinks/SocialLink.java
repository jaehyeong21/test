package com.example.demo.domain.SocialLinks;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "social_links")
@NoArgsConstructor
@Getter
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "url")
    private String url;

    @Builder
    public SocialLink(Long id, String type, String url){
        this.id = id;
        this.type = type;
        this.url = url;
    }
}

