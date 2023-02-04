package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DataResponseDto<T> {
    private final T data;
    public DataResponseDto (Boolean success, String message, T data) {
        this.data = data;
    }
}
