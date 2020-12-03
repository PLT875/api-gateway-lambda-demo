package com.friend.api;

import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class DynamoDBUserRepositoryTest {

    private DynamoDBUserRepository dynamoDBUserRepository;

    private User mockUser;

    @Mock
    private IDynamoDBMapper mockDynamoDBMapper;

    @Before
    public void setup() {
        Map<String, String> johnsFriendRequests = new HashMap<>();
        johnsFriendRequests.put("u1", "pending");
        johnsFriendRequests.put("u2", "accepted");
        mockUser = new User("u0", "john", johnsFriendRequests);

        MockitoAnnotations.initMocks(this);
        dynamoDBUserRepository = new DynamoDBUserRepository(mockDynamoDBMapper);
    }

    @Test
    public void shouldReturnUserIfPresent() {
        when(mockDynamoDBMapper.load(User.class, "u0")).thenReturn(mockUser);

        Optional<User> user = dynamoDBUserRepository.getUser("u0");
        assertTrue(user.isPresent());
        assertEquals("u0", user.get().getId());
        assertEquals("john", user.get().getName());
        assertEquals("pending", user.get().getFriendRequests().get("u1"));
        assertEquals("accepted", user.get().getFriendRequests().get("u2"));
    }
}
