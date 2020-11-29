# lambda-friend-demo

### Package

Package the app and upload the .jar to your already created Lambda
```
./mvnw clean package shade:shade
```

### Testing

Unit tests
```
./mvnw test
```

### Lambda black box tests

Test events were created based on the "Amazon API Gateway AWS Proxy" template

Get friend requests
```
{
  "pathParameters": {
    "id": "u1"
  },
  "path": "/user/{id}/friendRequests",
  "httpMethod": "GET",
  "protocol": "HTTP/1.1"
}

```