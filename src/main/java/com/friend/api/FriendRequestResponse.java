package com.friend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendRequestResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String state;
}
