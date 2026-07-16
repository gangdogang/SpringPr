package com.study.board.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long writerId;

    public PostRequestDto() {
    }

    public PostRequestDto(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }
}