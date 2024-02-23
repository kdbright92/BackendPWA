package com.example.BackendPWA.Post;

import com.example.BackendPWA.Comment.Comment;
import com.example.BackendPWA.Comment.CommentRepository;
import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

        @Autowired
        private final PostRepository postRepository;
        @Autowired
        private final PostService postService;

        @Autowired
        private final UserRepository userRepository;

        @Autowired
        private final CommentRepository commentRepository;

        @PostMapping("/create")
        public ResponseEntity<Post> createPost(@RequestBody Post post) {
                Post posts = new Post();
                posts.setTitle(posts.getTitle());
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUsername = authentication.getName();

                User user = userRepository.findByUsername(currentUsername).get();
                if (user == null) {
                        throw new RuntimeException("User not found with username: " + currentUsername);
                }

                post.setUser(user);


                if (post.getSelectedFile() != null && !post.getSelectedFile().isEmpty()) {
                        byte[] decodedBytes = Base64.getDecoder().decode(post.getSelectedFile());
                        post.setFile(decodedBytes);
                }

                Post savedPost = postService.savePost(post);



                return ResponseEntity.ok(savedPost);
        }

        @DeleteMapping("/{postId}")
        public void deletePost(@PathVariable long postId) {
                postService.deletePostById(postId);
        }




        @GetMapping("/getAll")
        public List<Post> getAll() {
               return postService.getPosts();
        }

        @GetMapping("/getUserPosts/{userId}")
        public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
                try {
                        List<Post> userPosts = postRepository.findByUserId(userId);
                        return ResponseEntity.ok(userPosts);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }







}






