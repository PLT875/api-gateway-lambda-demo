package com.friend.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockedUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    public MockedUserRepository() {
        loadUsers();
    }

    private void loadUsers() {
        Map<String, String> johnsFriendRequests = new HashMap<>();
        johnsFriendRequests.put("u1", "pending");
        johnsFriendRequests.put("u2", "accepted");
        johnsFriendRequests.put("u3", "rejected");
        User u0 = new User("u0", "john", johnsFriendRequests);
        users.put("u0", u0);

        Map<String, String> marksFriendRequests = new HashMap<>();
        marksFriendRequests.put("u2", "pending");
        User u1 = new User("u1", "mark", marksFriendRequests);
        users.put("u1", u1);

        User u2 = new User("u2", "jack", Collections.emptyMap());
        users.put("u2", u2);
    }

    @Override
    public Optional<User> getUser(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getId(), user);
    }


}
