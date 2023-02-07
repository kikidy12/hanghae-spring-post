package com.example.board.service;

import com.example.board.dto.PostDto;
import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;


    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto.PostRes> findPosts() {
        return repository.findAll().stream().map(v ->
                new PostDto.PostRes(v.getId(), v.getTitle(), v.getContent(), v.getAuthor())).toList();
    }

    public PostDto.PostRes findPost(Long id) {
        Post post = this.repository.findById(id).orElseThrow();
        return new PostDto.PostRes(post);
    }
    public Post findPostHasPassword(Long id, String password) {
        return repository.findByIdAndPassword(id, password).orElseThrow();
    }

    @Transactional
    public PostDto.PostRes addPost(PostDto.PostAdd dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setAuthor(dto.getAuthor());
        post.setContent(dto.getContent());
        post.setPassword(dto.getPassword());

        return new PostDto.PostRes(repository.save(post));
    }

    @Transactional
    public PostDto.PostRes updatePost(PostDto.PostUpdate dto, Long id) {
        Post post = this.findPostHasPassword(id, dto.getPassword());

        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return new PostDto.PostRes(post);
    }


    @Transactional
    public void deletePost(Long id, String password) {
        Post post = this.repository.findByIdAndPassword(id, password).orElseThrow();
        this.repository.deleteById(post.getId());
    }
}
