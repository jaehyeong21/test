package com.example.demo.domain.users;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // 데이터베이스의 테이블과 매핑
@Table(name = "users") // 엔티티가 매핑될 실제 DB 테이블 이름 지정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성
@Getter
public class Users {
    @Id // 엔티티 기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // insert 할 때 DB가 알아서 id 값 채워줌
    @Column(name = "id", updatable = false) // 한번 저장되면 수정 불가능
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Builder
    public Users(Long id, String name, String email, String password, LocalDateTime created_at){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }
}
