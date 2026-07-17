package com.study.board.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class CommentRequestDto {

    @NotBlank
    private String content;

    @NotNull
    private Long writerId;

    public CommentRequestDto(){
    }

    public CommentRequestDto(String content, Long writerId){
        this.content = content;
        this.writerId = writerId;
    }



}
