package com.example.board.dto;

import lombok.Getter;

@Getter
public class UpdatePostDto {
    private String title;

    private String content;

    private String author;

    private String password;
}
