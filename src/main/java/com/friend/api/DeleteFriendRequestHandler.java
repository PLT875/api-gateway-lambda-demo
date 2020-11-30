package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * DELETE /user/{id}/friendRequest/{senderId}
 */
public class DeleteFriendRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private FriendService friendService;

    public DeleteFriendRequestHandler () {
        friendService = new FriendServiceImpl(new MockedUserRepository());
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        Map<String, String> pathParameters = event.getPathParameters();
        friendService.deleteFriendRequest(pathParameters.get("id"), pathParameters.get("senderId"));

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return new APIGatewayProxyResponseEvent()
                .withHeaders(headers)
                .withStatusCode(204);

        // TODO 4xx responses
    }
}
