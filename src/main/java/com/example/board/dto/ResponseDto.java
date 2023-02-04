package com.example.board.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto<T> {
    private Integer code;
    private String message;
    private T data;

    public static<T> ResponseDto<T> res(final HttpStatus code, final String message) {
        return res(code, message, null);
    }

    public static<T> ResponseDto<T> res(final HttpStatus code, final String message, final T data) {
        return ResponseDto.<T>builder()
                .data(data)
                .code(code.value())
                .message(message)
                .build();
    }
}
