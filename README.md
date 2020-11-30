# lambda-friend-demo

Notes:
* The App uses a mock database
* Have tested both API gateway and Lambda integrations. Also see [screenshot](API_Gateway_Example.png)

### Package

Package the app and upload the .jar to your already created Lambdas
```
./mvnw clean package shade:shade
```

### Testing

Unit tests
```
./mvnw test
```

### Endpoints

Get friend requests (received) of a user
```
GET /user/{id}/friendRequests 

Response
- 200 OK
    [
        {
            "senderId": "<senderId>",
            "state": "<state>"
        }
    ]
```

e.g.
```
curl --location --request GET 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequests' --header 'Content-Type: application/json' | json_pp
[
    {
        "senderId": "u1",
        "state": "pending"
    },
    {
        "senderId": "u2",
        "state": "accepted",  
    }
]
```

Add friend request
```
POST /user/{id}/friendRequest

Request body
{
    "senderId": "<senderId>"
}

Response
- 204 No Content
```

e.g.
```
curl --location --request POST 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequest' \
--header 'Content-Type: application/json' \
--data-raw '{
    "senderId": "u3"
}'
```

Update friend request
```
POST /user/{id}/friendRequest

Request body
{
    "senderId": "<senderId>",
    "state": <state>
}

Response
- 204 No Content
```

e.g.
```
curl --location --request PUT 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequest' \
--header 'Content-Type: application/json' \
--data-raw '{
    "senderId": "u1",
    "state": "rejected"
}'
```

Delete friend request
```
DELETE /user/{id}/friendRequest/{senderId}

Response
- 204 No Content
```

e.g.
```
curl --location --request DELETE 'https://1kfao000mf.execute-api.eu-west-1.amazonaws.com/dev/user/u0/friendRequest/u2' \
--header 'Content-Type: application/json'
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
    "body": "{\"senderId\":\"u2\"}",
    "path": "/user/{id}/friendRequest",
    "httpMethod": "POST",
    "protocol": "HTTP/1.1"
}
```

Edit friend request
```
{
    "pathParameters": {
        "id": "u0"
    },
    "body": "{\"senderId\":\"u2\",\"state\":\"rejected\"}}",
    "path": "/user/{id}/friendRequest",
    "httpMethod": "PUT",
    "protocol": "HTTP/1.1"
}
```

Delete friend request
```
{
    "pathParameters": {
        "id": "u0",
        "senderId": "u2"
    },
    "path": "/user/{id}/friendRequest/{senderId}",
    "httpMethod": "DELETE",
    "protocol": "HTTP/1.1"
}
```