package com.study.board.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")   // ← 추가: DB 테이블 이름을 "users"로 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}