package com.study.board.DTO;

import jakarta.validation.constraints.NotBlank;

public class PostUpdateRequestDto {
    @NotBlank(message = "제목을 입력 해주세요")
    private String title;

    @NotBlank(message = "내용을 입력 해주세요.")
    private String content;

    public PostUpdateRequestDto() {
    }

    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
