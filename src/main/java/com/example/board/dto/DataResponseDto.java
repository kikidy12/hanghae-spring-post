package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DataResponseDto<T> extends ResponseDto {
    private final T data;
    public DataResponseDto (Boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }
}
