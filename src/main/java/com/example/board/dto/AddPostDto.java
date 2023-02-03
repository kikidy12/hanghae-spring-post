package com.example.board.dto;

import lombok.Getter;

@Getter
public class AddPostDto {
    private String title;

    private String content;

    private String author;

    private String password;
}
