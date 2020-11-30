package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DeleteFriendRequestHandlerTest {

    private DeleteFriendRequestHandler deleteFriendRequestHandler;

    // @Mock private FriendService

    @Mock
    private APIGatewayProxyRequestEvent mockRequestEvent;

    @Mock
    private Context context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        deleteFriendRequestHandler = new DeleteFriendRequestHandler();
    }

    @Test
    public void shouldReturn204() {
        when(mockRequestEvent.getPathParameters()).thenReturn(new HashMap<String, String>(){{
            put("id", "u0");
            put("senderId", "u2");
        }});

        APIGatewayProxyResponseEvent response = deleteFriendRequestHandler.handleRequest(mockRequestEvent, context);
        assertEquals("application/json", response.getHeaders().get("Content-Type"));
        assertEquals(204, response.getStatusCode().longValue());
    }

}
