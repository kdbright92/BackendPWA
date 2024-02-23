package com.example.BackendPWA.Post;

import com.example.BackendPWA.Comment.Comment;
import com.example.BackendPWA.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Builder
public class Post {
    @Id
    @GeneratedValue
    private long id;
//    private Date datum;
    private String title;
    @Lob
    @Column(columnDefinition = "LONGBLOB", length = 104857600)
    private byte[] file;
    @Lob
    private byte[] videoData; // Binärdaten des Videos
    private String encodedFile;
    private String selectedFile;
    private byte[] decodedFile;

    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
