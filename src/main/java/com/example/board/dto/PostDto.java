package com.example.board.dto;

import com.example.board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
public class PostDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostRes {
        private Long id;
        private String title;

        private String content;

        private String author;

        public PostRes(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.author = post.getAuthor();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostAdd {
        private String title;

        private String content;
        private String author;

        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostUpdate {
        private String title;

        private String content;

        private String author;

        private String password;
    }
}
