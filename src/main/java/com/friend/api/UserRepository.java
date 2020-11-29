package com.friend.api;

import java.util.Optional;

/**
 * Can support different implementations e.g. in-memory (test), DynamoDB, PostgreSQL, etc.
 */
public interface UserRepository {

    Optional<User> getUser(String id);

    void updateUser(User user);
}
