package com.friend.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FriendServiceTest {

    private FriendService friendService;

    @Before
    public void setup() {
        UserRepository userRepository = new MockedUserRepository();
        friendService = new FriendService(userRepository);
    }

    @Test
    public void shouldReturnFriendRequests() {
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(3, u0.size());
        assertEquals("pending", u0.get("u1"));
        assertEquals("accepted", u0.get("u2"));
        assertEquals("rejected", u0.get("u3"));

        Map<String, String> u1 = friendService.getFriendRequests("u1");
        assertEquals(1, u1.size());
        assertEquals("pending", u1.get("u2"));

        Map<String, String> u2 = friendService.getFriendRequests("u2");
        assertTrue(u2.isEmpty());
    }

    @Test
    public void shouldReturnEmptyFriendRequestForUnknownUser() {
        Map<String, String> u4 = friendService.getFriendRequests("u4");
        assertTrue(u4.isEmpty());
    }


}
