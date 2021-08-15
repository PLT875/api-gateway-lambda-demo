package com.friend.api;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "user")
public class User {

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    // friend requests (received)
    @DynamoDBAttribute(attributeName = "friendRequests")
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
