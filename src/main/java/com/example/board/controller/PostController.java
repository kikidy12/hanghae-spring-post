package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.entity.Post;
import com.example.board.service.PostService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "post")
@Controller
@RequestMapping(value = "/api/post")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public ResponseDto list() {
        List<PostDto> list = service.findPosts().stream().map(v ->
                new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor())).toList();
        return new DataResponseDto<>(true, "조회 성공", list);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseDto one(@PathVariable("id") Long id) {
        Optional<Post> post = service.findPost(id);
        Optional<PostDto> data = post.map(v ->
                new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor()));
        return new DataResponseDto<>(true, "조회 성공", data);
    }

    @PostMapping
    @ResponseBody
    public ResponseDto addPost(@RequestBody AddPostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setAuthor(dto.getAuthor());
        post.setContent(dto.getContent());
        post.setPassword(dto.getPassword());

        Post res = service.addPost(post);
        PostDto data = new PostDto(res.getId(), res.getTitle(), res.getContent(), res.getAuthor());

        return new DataResponseDto<>(true, "등록 성공", data);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseDto updatePost(@PathVariable("id") Long id, @RequestBody UpdatePostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setAuthor(dto.getAuthor());
        post.setContent(dto.getContent());
        post.setPassword(dto.getPassword());

        Optional<Post> result = service.updatePost(post ,id);

        if (result.isEmpty()) {
            return ResponseDto.of(false, "수정 실패");
        }

        Optional<PostDto> data = result.map(v ->
                new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor()));

        return new DataResponseDto<>(true, "수정 성공", data);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable("id") Long id, @RequestParam String password) {
        if (service.deletePost(id, password)) {
            return ResponseDto.of(true, "삭제 성공");
        }
        else {
            return ResponseDto.of(false, "삭제 실패");
        }

    }
}
