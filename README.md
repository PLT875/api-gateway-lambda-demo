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

### Endpoints

Get friend requests
```
GET /user/{id}/friendRequests 
200 OK
[
    {
        "id": "<id of user of sent friend request>",
        "state": "<state>"
    }
]
```

e.g.
```
curl --location --request GET 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequests' --header 'Content-Type: application/json' | json_pp
[
   {
      "id": "u1",
      "state": "pending"
   },
   {
      "state": "accepted",
      "id": "u2"
   }
]

```

Add friend request
```
POST /user/{id}/friendRequest
204 No Content
{
    "id": "<id of user to send friend request>"
}
```

e.g.
```
curl --location --request POST 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequest' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "u3"
}'
```

### Lambda black box tests

Based on the "Amazon API Gateway AWS Proxy" template

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

Add friend request
```
{
  "pathParameters": {
    "id": "u0"
  },
  "body": "{\"id\":\"u2\"}",
  "path": "/user/{id}/friendRequest",
  "httpMethod": "POST",
  "protocol": "HTTP/1.1"
}
```