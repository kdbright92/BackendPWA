package com.example.BackendPWA.Friends;


import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendsController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendsServiceImpl friendshipService;

    @GetMapping("/requests/pending/{id}")
    public ResponseEntity<List<Friends>> getPendingFriendRequests(@PathVariable long id)
            {
        try {
            List<Friends> pendingRequests = friendshipService.getPendingFriendRequests(id);
            return ResponseEntity.ok(pendingRequests);
        } catch (AccessDeniedException e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/sendRequest/{id}")
    public ResponseEntity<Long> sendFriendRequest(@PathVariable long id) {
        friendshipService.sendFriendRequest(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/acceptRequest/{id}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long id) {
        friendshipService.acceptFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rejectRequest/{id}")
    public ResponseEntity<Void> rejectFriendRequest(@PathVariable Long id) {
        friendshipService.rejectFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friends")
    public ResponseEntity<List<User>> getUserFriendList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new UserIDNotFoundException(currentUsername));

        List<User> friendList = friendshipService.getFriendList(user.getId());
        return ResponseEntity.ok(friendList);
    }

    @GetMapping("/friends/{id}")
    public ResponseEntity<List<User>> getUserFriendListfromUser(@PathVariable Long id) {
        List<User> friendList = friendshipService.getFriendList(id);
        return ResponseEntity.ok(friendList);
    }

}
