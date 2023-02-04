package com.example.board.service;

import com.example.board.dto.AddPostDto;
import com.example.board.dto.PostDto;
import com.example.board.dto.UpdatePostDto;
import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;


    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> findPosts() {
        return repository.findAll().stream().map(v ->
                new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor())).toList();
    }

    public Optional<PostDto> findPost(Long id) {
        return this.findPostHasPassword(id).map(v ->
                new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor()));
    }
    public Optional<Post> findPostHasPassword(Long id) {
        return repository.findById(id);
    }

    public PostDto addPost(AddPostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setAuthor(dto.getAuthor());
        post.setContent(dto.getContent());
        post.setPassword(dto.getPassword());

        post = repository.save(post);
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor());
    }

    public Optional<PostDto> updatePost(UpdatePostDto post, Long id) {
        Optional<Post> findPost = this.findPostHasPassword(id);
        if (findPost.isEmpty() || !findPost.get().getPassword().equals(post.getPassword())) {
            return Optional.empty();
        }

        return findPost.map(v -> new PostDto(v.getId(), v.getTitle(), v.getContent(), v.getAuthor()));
    }


    public Boolean deletePost(Long id, String password) {
        Optional<Post> post = this.findPostHasPassword(id);
        if (post.isEmpty() || !post.get().getPassword().equals(password)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
