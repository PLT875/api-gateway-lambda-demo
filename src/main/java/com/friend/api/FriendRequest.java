package com.friend.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {

    @JsonProperty("senderId")
    private String senderId;

    @JsonProperty("state")
    private String state;
}
