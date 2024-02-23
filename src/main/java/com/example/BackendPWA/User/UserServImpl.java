package com.example.BackendPWA.User;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public User saveUser(User user) {

            user = user.builder()
                    .roles(Roles.USER)
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .country(user.getCountry())
                    .username(user.getUsername())
                    .password(encoder.encode(user.getPassword()))
                    .profilePicture(user.getProfilePicture())
                    .id(user.getId())
                    .country(user.getCountry())
                    .city(user.getCity())

                    .build();

            return userRepo.save(user);


        }



    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public byte[] getImageData(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            byte[] imageData = user.getProfilePicture();
            return imageData;
        } else {

            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User register(User user) {
        if (!userRepo.existsByUsername(user.getUsername())) {
            user.setPassword(encoder.encode(user.getPassword()));

            return userRepo.save(user);
        } else {
            return new User();

        }
    }
    public User updateUser(Long userId, String newUsername, String newPassword, String newCity, String newCountry, String selectedFile) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(newUsername);
        user.setCity(newCity);
        user.setCountry(newCountry);

        if (newPassword != null && !newPassword.isEmpty()) {
            String encryptedPassword = encoder.encode(newPassword);
            user.setPassword(encryptedPassword);
        }
        if(selectedFile != null && !selectedFile.isEmpty()) {
            byte[] decodedBytes = Base64.getDecoder().decode(selectedFile);
            user.setProfilePicture(decodedBytes);
        }

        return userRepo.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).get();
    }

    @Override
    public boolean emailExists(String email) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User nicht verfügbar");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


}