package com.example.BackendPWA.Friends;

public class FriendRequestDTO {

    private Long requesterId;
    private Long accepterId;

    // Constructors, getters, setters

    public FriendRequestDTO() {
    }

    public FriendRequestDTO(Long requesterId, Long accepterId) {
        this.requesterId = requesterId;
        this.accepterId = accepterId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(Long accepterId) {
        this.accepterId = accepterId;
    }
}
