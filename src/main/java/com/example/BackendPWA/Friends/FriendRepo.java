package com.example.BackendPWA.Friends;


import com.example.BackendPWA.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepo extends JpaRepository<Friends,Long> {

    List<Friends> findByRequesterOrAccepter(User requester, User accepter);

    List<Friends> findByAccepterIdAndStatus(long id, FriendshipStatus status);

    List<Friends> findByRequesterAndStatusOrAccepterAndStatus(User user, FriendshipStatus accepted, User user1, FriendshipStatus accepted1);


}
