package com.study.board.Domain;

import com.study.board.Domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Lob
    private String content;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Builder
    public Post(String title, String content, User writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = 0L;
        this.likeCount = 0L;
    }

    public void increaseViewCount(){
        this.viewCount++;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}