package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private final Boolean success;
    private final String message;

    public static ResponseDto of(Boolean success, String message) {
        return new ResponseDto(success, message);
    }
}
