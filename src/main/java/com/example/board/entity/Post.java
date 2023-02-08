package com.example.board.entity;

import com.example.board.dto.PostDto;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String content;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    String password;

    @Builder
    public Post(PostDto.PostAdd dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = dto.getAuthor();
        this.password = dto.getPassword();
    }

    public void update(PostDto.PostUpdate dto) {
        if (dto.getTitle() != null) {
            this.content = dto.getTitle();
        }

        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }

        if (dto.getAuthor() != null) {
            this.content = dto.getAuthor();
        }
    }


}
