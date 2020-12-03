package com.friend.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GetFriendRequestsHandlerTest {

    private GetFriendRequestsHandler getFriendRequestsHandler;

    @Mock
    private FriendService friendService;

    @Mock
    private APIGatewayProxyRequestEvent mockRequestEvent;

    @Mock
    private Context context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        getFriendRequestsHandler = new GetFriendRequestsHandler(friendService);
    }

    @Test
    public void shouldReturn200AndFriendRequests() {
        when(mockRequestEvent.getPathParameters()).thenReturn(new HashMap<String, String>(){{ put("id", "u0"); }});
        when(friendService.getFriendRequests("u0")).thenReturn(new HashMap<String, String>(){{
            put("u1", "pending");
            put("u2", "accepted");
        }});

        APIGatewayProxyResponseEvent response = getFriendRequestsHandler.handleRequest(mockRequestEvent, context);
        assertEquals("application/json", response.getHeaders().get("Content-Type"));
        assertEquals(200, response.getStatusCode().longValue());
        assertEquals("[{\"senderId\":\"u1\",\"state\":\"pending\"},{\"senderId\":\"u2\",\"state\":\"accepted\"}]", response.getBody());
    }

    @Test
    public void shouldReturn200AndEmptyIfNoFriendRequests() {
        when(mockRequestEvent.getPathParameters()).thenReturn(new HashMap<String, String>(){{ put("id", "u2"); }});
        when(friendService.getFriendRequests("u0")).thenReturn(Collections.emptyMap());

        APIGatewayProxyResponseEvent response = getFriendRequestsHandler.handleRequest(mockRequestEvent, context);
        assertEquals("application/json", response.getHeaders().get("Content-Type"));
        assertEquals(200, response.getStatusCode().longValue());
        assertEquals("[]", response.getBody());
    }

}
