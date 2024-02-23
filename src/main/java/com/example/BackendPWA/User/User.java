package com.example.BackendPWA.User;

import com.example.BackendPWA.Comment.Comment;
import com.example.BackendPWA.Friends.Friends;
import com.example.BackendPWA.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.sql.ConnectionBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private String firstname;
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Lob
    @Column(columnDefinition = "LONGBLOB", length = 20971520)
    private byte[] profilePicture;

    private String email;
    private String country;
    private String city;
    private String selectedFile;
    private String bio;


    @OneToMany
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;


    @OneToMany(mappedBy = "requester")
    @JsonIgnoreProperties({"requester","accepter"})
    private List<Friends> friendshipsInitiated;

    @OneToMany(mappedBy = "accepter")
    @JsonIgnoreProperties({"requester","accepter"})
    private List<Friends> friendshipsAccepted;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}