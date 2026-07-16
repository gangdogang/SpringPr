package com.study.board.DTO;

public class ErrorResponseDto {
    private int status;
    private String message;

    //응답용이라 없어도 됨
    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
