package com.friend.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private String id;

    private String name;

    // friend requests send to another user and their state
    private Map<String, String> friendRequests;

}
