package com.example.BackendPWA.Post;

import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Post savePost(Post post) {

        post = Post.builder()
                .title(post.getTitle())
                .id(post.getId())
                .user(post.getUser())
                .file(post.getFile())
                .comments(post.getComments())
                .build();


        return postRepository.save(post);
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();



    }

    @Override
    public boolean deletePostById(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}






