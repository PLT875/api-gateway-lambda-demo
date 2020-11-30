package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class AddFriendRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private FriendService friendService;

    public AddFriendRequestHandler() {
        friendService = new FriendServiceImpl(new MockedUserRepository());
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        Map<String, String> pathParameters = event.getPathParameters();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FriendRequest friendRequest = objectMapper.readValue(event.getBody(), FriendRequest.class);
            friendService.addFriendRequest(pathParameters.get("id"), friendRequest.getId());

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            return new APIGatewayProxyResponseEvent()
                    .withHeaders(headers)
                    .withStatusCode(204);

            // TODO 4xx responses
        } catch (JsonProcessingException e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500);
        }
    }
}
