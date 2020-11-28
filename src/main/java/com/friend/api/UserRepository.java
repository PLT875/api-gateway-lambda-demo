package com.friend.api;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUser(String id);

    void updateUser(User user);
}
