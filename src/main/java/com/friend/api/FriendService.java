package com.friend.api;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class FriendService {

    private UserRepository userRepository;

    public FriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Map<String, String> getFriendRequests(String userId) {
        Map<String, String> friendRequest = Collections.emptyMap();
        Optional<User> user = userRepository.getUser(userId);

        if (user.isPresent()) {
            return user.get().getFriendRequests();
        }

        return friendRequest;
    }

    void addFriendRequest(String userId, String requestUserId) {

    }

    void updateFriendRequest(String userId, String updateUserId, String state) {

    }

    void deleteFriendRequest(String userId, String deleteUserId) {

    }
}
