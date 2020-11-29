package com.friend.api;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class FriendServiceImplTest {

    private FriendService friendService;

    @Before
    public void setup() {
        UserRepository userRepository = new MockedUserRepository();
        friendService = new FriendServiceImpl(userRepository);
    }

    @Test
    public void shouldReturnFriendRequests() {
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
        assertEquals("pending", u0.get("u1"));
        assertEquals("accepted", u0.get("u2"));

        Map<String, String> u1 = friendService.getFriendRequests("u1");
        assertEquals(1, u1.size());
        assertEquals("pending", u1.get("u2"));

        Map<String, String> u2 = friendService.getFriendRequests("u2");
        assertTrue(u2.isEmpty());
    }

    @Test
    public void shouldReturnEmptyFriendRequestsForUnknownUser() {
        Map<String, String> u4 = friendService.getFriendRequests("u4");
        assertTrue(u4.isEmpty());
    }

    @Test
    public void shouldAddFriendRequest() {
        friendService.addFriendRequest("u0", "u3");
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(3, u0.size());
        assertEquals("pending", u0.get("u1"));
        assertEquals("accepted", u0.get("u2"));
        assertEquals("pending", u0.get("u3"));

        friendService.addFriendRequest("u2", "u3");
        Map<String, String> u2 = friendService.getFriendRequests("u2");
        assertEquals(1, u2.size());
        assertEquals("pending", u2.get("u3"));
    }

    @Test
    public void shouldNotAddFriendRequestIfAlreadyAdded() {
        friendService.addFriendRequest("u0", "u2");
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
        assertEquals("accepted", u0.get("u2"));
    }

    @Test
    public void shouldNotAddFriendRequestForUnknownUser() {
        friendService.addFriendRequest("u0", "unknown");
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
    }

    @Test
    public void shouldUpdateFriendRequestState() {
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals("pending", u0.get("u1"));

        friendService.updateFriendRequestState("u0", "u1", "accepted");
        u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
        assertEquals("accepted", u0.get("u1"));

        friendService.updateFriendRequestState("u0", "u1", "rejected");
        u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
        assertEquals("rejected", u0.get("u1"));

        friendService.updateFriendRequestState("u0", "u1", "pending");
        u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
        assertEquals("rejected", u0.get("u1"));
    }

    @Test
    public void shouldNotUpdateFriendRequestStateForUnknownUser() {
        friendService.updateFriendRequestState("u0", "unknown", "accepted");
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(2, u0.size());
    }

    @Test
    public void shouldDeleteFriendRequest() {
        friendService.deleteFriendRequest("u0", "u2");
        Map<String, String> u0 = friendService.getFriendRequests("u0");
        assertEquals(1, u0.size());
        assertFalse(u0.containsKey("u2"));
        assertTrue(u0.containsKey("u1"));
    }

}
