package com.friend.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;

import java.util.Optional;

public class DynamoDBUserRepository implements UserRepository {

    private IDynamoDBMapper dynamoDBMapper;

    public DynamoDBUserRepository() {
        this.dynamoDBMapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }

    public DynamoDBUserRepository(IDynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Optional<User> getUser(String id) {
        return Optional.ofNullable(dynamoDBMapper.load(User.class, id));
    }

    @Override
    public void updateUser(User user) {

    }
}
