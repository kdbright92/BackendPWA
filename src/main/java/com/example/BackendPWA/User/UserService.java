package com.example.BackendPWA.User;


import java.util.List;

public interface UserService {
    User saveUser(User user);

    User register(User user);

    User findByUsername(String username);

    boolean emailExists(String email);

    void deleteAllUsers();

    User getUserById(long id);

    byte[] getImageData(Long userId);

    List<User> getAllUsers();

    User updateUser(Long userId, String newUsername, String newPassword,String selectedFile, String newCity, String newCountry);
}
