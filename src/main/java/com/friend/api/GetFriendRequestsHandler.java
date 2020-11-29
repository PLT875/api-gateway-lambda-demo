package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GET /user/{id}/friendRequests
 *
 * Uses Lambda proxy integration
 */
public class GetFriendRequestsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private FriendService friendService;

    public GetFriendRequestsHandler() {
        friendService = new FriendServiceImpl(new MockedUserRepository());
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        Map<String, String> pathParameters = event.getPathParameters();
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> friendRequests = friendService.getFriendRequests(pathParameters.get("id"));
            String response = objectMapper.writeValueAsString(toResponseBody(friendRequests));

            return new APIGatewayProxyResponseEvent()
                    .withHeaders(headers)
                    .withStatusCode(200)
                    .withBody(response);

            // TODO 404 if user id not found
        } catch (JsonProcessingException e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500);
        }
    }

    private List<FriendRequest> toResponseBody(Map<String, String> friendRequest) {
        return friendRequest
                .entrySet()
                .stream()
                .map(f -> new FriendRequest(f.getKey(), f.getValue()))
                .collect(Collectors.toList());
    }
}