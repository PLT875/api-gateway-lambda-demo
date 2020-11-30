package com.friend.api;

import java.util.*;

public class FriendServiceImpl implements FriendService {

    private UserRepository userRepository;

    public static List<String> VALID_UPDATE_STATES = Arrays.asList("accepted", "rejected");

    public FriendServiceImpl(UserRepository userRepository) {
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

    public void addFriendRequest(String userId, String senderUserId) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> sender = userRepository.getUser(senderUserId);

        // TODO handle invalid users
        if (user.isPresent() && sender.isPresent()) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            if (!friendRequests.containsKey(senderUserId)) {
                friendRequests.put(senderUserId, "pending");
                userRepository.updateUser(user.get());
            }
        }
    }

    public void updateFriendRequestState(String userId, String senderUserId, String state) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> sender = userRepository.getUser(senderUserId);

        // TODO handle invalid users and states
        if (user.isPresent() && sender.isPresent() && VALID_UPDATE_STATES.contains(state)) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            if (friendRequests.containsKey(senderUserId)) {
                friendRequests.put(senderUserId, state);
                userRepository.updateUser(user.get());
            }
        }
    }

    public void deleteFriendRequest(String userId, String senderUserId) {
        Optional<User> user = userRepository.getUser(userId);
        Optional<User> sender = userRepository.getUser(senderUserId);

        // TODO handle invalid users
        if (user.isPresent() && sender.isPresent()) {
            Map<String, String> friendRequests = user.get().getFriendRequests();
            friendRequests.remove(senderUserId);
            userRepository.updateUser(user.get());
        }
    }
}
