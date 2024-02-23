package com.example.BackendPWA.Comment;

public interface CommentService {
    Comment createComment(Comment comment);


    boolean deleteCommentById(Long id);


}
