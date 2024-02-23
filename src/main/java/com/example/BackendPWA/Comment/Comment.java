package com.example.BackendPWA.Comment;

import com.example.BackendPWA.Post.Post;
import com.example.BackendPWA.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Builder
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
