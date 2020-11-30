package com.friend.api;

import java.util.Map;

public interface FriendService {

    Map<String, String> getFriendRequests(String userId);

    void addFriendRequest(String userId, String senderUserId);

    void updateFriendRequestState(String userId, String senderUserId, String state);

    void deleteFriendRequest(String userId, String senderUserId);
}
