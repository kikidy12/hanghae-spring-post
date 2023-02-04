package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<ResponseDto<List<PostDto>>> list() {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "조회 성공", service.findPosts()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto<Optional<PostDto>>> one(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "조회 성공", service.findPost(id)));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseDto<PostDto>> addPost(@RequestBody AddPostDto dto) {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "등록 성공",service.addPost(dto)));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto<PostDto>> updatePost(@PathVariable("id") Long id, @RequestBody UpdatePostDto dto) {
        Optional<PostDto> result = service.updatePost(dto ,id);

        return result.map(postDto ->
                ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "수정 성공", postDto))).orElseGet(() ->
                ResponseEntity.badRequest().body(ResponseDto.res(HttpStatus.BAD_REQUEST, "수정 실패")));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto<Boolean>> delete(@PathVariable("id") Long id, @RequestParam String password) {
        if (service.deletePost(id, password)) {
            return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "삭제 성공", true));
        }
        else {
            return ResponseEntity.ok(ResponseDto.res(HttpStatus.BAD_REQUEST, "삭제 실패", false));
        }
    }
}
