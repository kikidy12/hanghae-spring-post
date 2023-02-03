package com.example.board.service;

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

    public List<Post> findPosts() {
        return repository.findAll();
    }

    public Optional<Post> findPost(Long id) {
        return repository.findById(id);
    }

    public Post addPost(Post post) {
        return repository.save(post);
    }

    public Optional<Post> updatePost(Post post, Long id) {
        Optional<Post> findPost = this.findPost(id);
        if (findPost.isEmpty() || !findPost.get().getPassword().equals(post.getPassword())) {
            return Optional.empty();
        }

        findPost.get().setTitle(post.getTitle());
        findPost.get().setContent(post.getContent());
        findPost.get().setAuthor(post.getAuthor());

        return Optional.of(repository.save(findPost.get()));
    }


    public Boolean deletePost(Long id, String password) {
        Optional<Post> post = this.findPost(id);
        if (post.isEmpty() || !post.get().getPassword().equals(password)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
