package com.friend.api;

import java.util.Map;

public interface FriendService {

    Map<String, String> getFriendRequests(String userId);

    void addFriendRequest(String userId, String requestUserId);

    void updateFriendRequest(String userId, String updateUserId, String state);

    void deleteFriendRequest(String userId, String deleteUserId);
}
