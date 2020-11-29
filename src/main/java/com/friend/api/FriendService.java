package com.friend.api;

import java.util.*;

public class FriendService {

    private UserRepository userRepository;

    public static List<String> VALID_UPDATE_STATES = Arrays.asList("accepted", "rejected");

    public FriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, String> getFriendRequests(String userId) {
        Map<String, String> friendRequest = Collections.emptyMap();
        Optional<User> user = userRepository.getUser(userId);

        if (user.isPresent()) {
            return user.get().getFriendRequests();
        }

        return friendRequest;
    }

    public void addFriendRequest(String userId, String requestUserId) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> requestUser = userRepository.getUser(requestUserId);

        if (user.isPresent() && requestUser.isPresent()) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            if (!friendRequests.containsKey(requestUserId)) {
                friendRequests.put(requestUserId, "pending");
                userRepository.updateUser(user.get());
            }
        }
    }

    public void updateFriendRequestState(String userId, String updateUserId, String state) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> requestUser = userRepository.getUser(updateUserId);

        if (user.isPresent() && requestUser.isPresent() && VALID_UPDATE_STATES.contains(state)) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            if (friendRequests.containsKey(updateUserId)) {
                friendRequests.put(updateUserId, state);
                userRepository.updateUser(user.get());
            }
        }
    }

    public void deleteFriendRequest(String userId, String deleteUserId) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> requestUser = userRepository.getUser(deleteUserId);

        if (user.isPresent() && requestUser.isPresent()) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            friendRequests.remove(deleteUserId);
            userRepository.updateUser(user.get());
        }
    }
}
