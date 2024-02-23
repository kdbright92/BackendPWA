package com.example.BackendPWA.Friends;


import com.example.BackendPWA.User.User;
import com.example.BackendPWA.User.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendsServiceImpl {
    @Autowired
    private FriendRepo friendshipRepository;

    @Autowired
    private UserRepository userRepository;

/*    public List<User> getPendingFriendRequests(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).get();

        return friendshipRepository.findByAccepterIdAndStatus(user.getId(), FriendshipStatus.PENDING).stream().map(f -> f.getRequester()).collect(Collectors.toList());


    }*/
public List<Friends> getPendingFriendRequests(long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();
    User user = userRepository.findByUsername(currentUsername).orElseThrow(() -> new UserIDNotFoundException(currentUsername));

    return friendshipRepository.findByAccepterIdAndStatus(user.getId(), FriendshipStatus.PENDING);
}


    public long sendFriendRequest(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User requester = userRepository.findByUsername(currentUsername).get();
        User accepter = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Friends friendship = new Friends();
        friendship.setRequester(requester);
        friendship.setAccepter(accepter);
        friendship.setStatus(FriendshipStatus.PENDING);

        friendshipRepository.save(friendship);

        return friendship.getId();
    }

    public void acceptFriendRequest(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User accepter = userRepository.findByUsername(currentUsername).orElseThrow(() -> new UserIDNotFoundException(currentUsername));
        Friends friendship = friendshipRepository.findById(id).orElseThrow(() -> new FriendsNotFoundException(id));
        friendship.setStatus(FriendshipStatus.ACCEPTED);


        friendshipRepository.save(friendship);
        List<User> friendList = getFriendList(accepter.getId());
    }

    public void rejectFriendRequest(Long id) {
        Friends friendship = friendshipRepository.findById(id).orElseThrow(() -> new FriendsNotFoundException(id));
        friendship.setStatus(FriendshipStatus.REJECTED);
        friendshipRepository.save(friendship);
    }


    public List<User> getFriendList(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<Friends> acceptedFriendships = friendshipRepository.findByRequesterAndStatusOrAccepterAndStatus(user, FriendshipStatus.ACCEPTED, user, FriendshipStatus.ACCEPTED);
        return acceptedFriendships.stream()
                .map(friendship -> friendship.getRequester().equals(user) ? friendship.getAccepter() : friendship.getRequester())
                .collect(Collectors.toList());
    }

    public List<Friends> getFriends() {
        return friendshipRepository.findAll();
    }


    public List<User> getFriendListFromUser(Long id) {
        // Assuming FriendshipRepository has a method to fetch friends by user ID
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<Friends> friendships = friendshipRepository.findByRequesterAndStatusOrAccepterAndStatus(user, FriendshipStatus.ACCEPTED, user, FriendshipStatus.ACCEPTED);

        // Extract the friend users from the friendships
        List<User> friendList = friendships.stream()
                .map(friendship -> friendship.getRequester().equals(user) ? friendship.getAccepter() : friendship.getRequester())
                .collect(Collectors.toList());

        return friendList;
    }

}

