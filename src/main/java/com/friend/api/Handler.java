package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;

public class Handler {
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        return "Hello World - " + input;
    }
}