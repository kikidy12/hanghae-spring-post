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
import lombok.RequiredArgsConstructor;
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
        return new DataResponseDto<>(true, "조회 성공", service.findPosts());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseDto one(@PathVariable("id") Long id) {
        return new DataResponseDto<>(true, "조회 성공", service.findPost(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseDto addPost(@RequestBody AddPostDto dto) {
        return new DataResponseDto<>(true, "등록 성공", service.addPost(dto));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseDto updatePost(@PathVariable("id") Long id, @RequestBody UpdatePostDto dto) {
        Optional<PostDto> result = service.updatePost(dto ,id);

        if (result.isEmpty()) {
            return ResponseDto.of(false, "수정 실패");
        }

        return new DataResponseDto<>(true, "수정 성공", result);
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
