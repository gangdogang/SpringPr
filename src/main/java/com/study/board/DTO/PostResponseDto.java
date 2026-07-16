package com.study.board.DTO;

public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writerNickname;
    private Long viewCount;
    private Long likeCount;

    public PostResponseDto(Long id, String title, String content, String writerNickname, Long viewCount, Long likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerNickname = writerNickname;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriterNickname() {
        return writerNickname;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }
}