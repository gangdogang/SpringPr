package com.study.board.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String content;

    @NotNull
    private String writerNickname;


    public CommentResponseDto(){}


    public CommentResponseDto(Long id, String content, String writerNickname) {
        this.id = id;
        this.content = content;
        this.writerNickname = writerNickname;
    }

}
