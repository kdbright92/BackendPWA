package com.example.BackendPWA.Comment;

import com.example.BackendPWA.Post.Post;
import com.example.BackendPWA.Post.PostRepository;
import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
// CommentController.java

public class CommentController {
@Autowired
    private final CommentService commentService;
@Autowired
    private final PostRepository postRepository;
@Autowired
    private final UserRepository userRepository;


    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable long postId, @RequestBody Comment comment) {
        Comment newComment = new Comment();
        newComment.setText(comment.getText());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userRepository.findByUsername(currentUsername).get();
        if (user == null) {
            throw new RuntimeException("User not found with username: " + currentUsername);
        }

        newComment.setUser(user);

        Optional<Post> optionalPost = postRepository.findById(postId);

        newComment.setPost(optionalPost.get());

        Comment savedComment = commentService.createComment(newComment);


        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable long commentId) {
        commentService.deleteCommentById(commentId);
    }
}





