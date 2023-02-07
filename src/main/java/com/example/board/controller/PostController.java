package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
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
    public ResponseEntity<ResponseDto<List<PostDto.PostRes>>> list() {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "조회 성공", service.findPosts()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto<PostDto.PostRes>> one(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "조회 성공", service.findPost(id)));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseDto<PostDto.PostRes>> addPost(@RequestBody @Valid PostDto.PostAdd dto) {
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "등록 성공",service.addPost(dto)));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto<PostDto.PostRes>> updatePost(@PathVariable("id") Long id, @RequestBody PostDto.PostUpdate dto) {

        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "수정 성공", service.updatePost(dto, id)));
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id, @RequestParam String password) {
        service.deletePost(id, password);
        return ResponseEntity.ok(ResponseDto.res(HttpStatus.OK, "삭제 성공", null));
    }
}
