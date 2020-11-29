package com.friend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FriendRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String state;
}
