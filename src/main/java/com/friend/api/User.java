package com.friend.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String id;

    private String name;

    // friend requests sent to another user and current state
    private Map<String, String> friendRequests;

    /**
     * This is only for test purposes for the MockedUserRepository
     *
     * @param user
     * @return copy of user
     */
    public static User copy(User user) {
        Map<String, String> copyFriendRequests = new HashMap<>();
        copyFriendRequests.putAll(user.friendRequests);

        return new User(user.id, user.name, copyFriendRequests);
    }
}
