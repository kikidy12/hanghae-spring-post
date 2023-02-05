package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PostDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        private Long id;
        private String title;

        private String content;

        private String author;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Add {
        private String title;

        private String content;

        private String author;

        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private String title;

        private String content;

        private String author;

        private String password;
    }
}
