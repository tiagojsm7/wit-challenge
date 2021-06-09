# Introduction

This project uses Spring Boot and RabbitMQ to create the backend of a calculator's rest API. 
The rabbitMQ instance was handled by Docker running the RabbitMQ image (https://hub.docker.com/_/rabbitmq/) locally.

## API Docs

```http
GET /{operation}?a={number1}&b={number2}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `operation` | `string` | **Required**. Operation to perform. Can take the following values: `sum`,`subtr`,`div`,`multi`. |
| `number1` | `number` | **Required**. First number of the operation. |
| `number2` | `number` | **Required**. Second number of the operation. |


## Responses

The response is a JSON object with the elements `result` and `message`. 

```javascript
{
  "result" : number,
  "message" : string
}
```

The `message` attribute contains a message commonly used to indicate errors. If there are no errors message should be "Ok".

The `result` attribute contains the operation result returned by the API. If an error occurs result will be "null".
