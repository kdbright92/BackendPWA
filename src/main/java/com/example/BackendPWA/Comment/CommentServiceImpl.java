package com.example.BackendPWA.Comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {

        comment = Comment.builder()
                .text(comment.getText())
                .user(comment.getUser())
                .post(comment.getPost())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteCommentById(Long id) {
        try {
            commentRepository.deleteById(id);
            return true; // Deletion successful
        } catch (Exception e) {
            // Handle exceptions or log errors if needed
            return false; // Deletion failed
        }
    }


    // Additional service methods if needed
}
