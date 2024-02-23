package com.example.BackendPWA.Post;

import com.example.BackendPWA.User.User;

import java.util.List;


public interface PostService {

    Post savePost(Post post);


    List<Post>getPosts();

    boolean deletePostById(Long id);






}
