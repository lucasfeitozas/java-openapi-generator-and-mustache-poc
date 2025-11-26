# GreetingsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createGreeting**](GreetingsApi.md#createGreeting) | **POST** /greetings | Persiste uma saudação customizada |
| [**getGreeting**](GreetingsApi.md#getGreeting) | **GET** /greetings/{name} | Recupera uma saudação personalizada |



## createGreeting

> GreetingResponse createGreeting(greetingRequest)

Persiste uma saudação customizada

### Example

```java
// Import classes:
import com.example.openapi.sdk.invoker.ApiClient;
import com.example.openapi.sdk.invoker.ApiException;
import com.example.openapi.sdk.invoker.Configuration;
import com.example.openapi.sdk.invoker.models.*;
import com.example.openapi.sdk.api.GreetingsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        GreetingsApi apiInstance = new GreetingsApi(defaultClient);
        GreetingRequest greetingRequest = new GreetingRequest(); // GreetingRequest | 
        try {
            GreetingResponse result = apiInstance.createGreeting(greetingRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GreetingsApi#createGreeting");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **greetingRequest** | [**GreetingRequest**](GreetingRequest.md)|  | |

### Return type

[**GreetingResponse**](GreetingResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Saudação criada |  -  |
| **400** | Erro de validação |  -  |


## getGreeting

> GreetingResponse getGreeting(name, title)

Recupera uma saudação personalizada

### Example

```java
// Import classes:
import com.example.openapi.sdk.invoker.ApiClient;
import com.example.openapi.sdk.invoker.ApiException;
import com.example.openapi.sdk.invoker.Configuration;
import com.example.openapi.sdk.invoker.models.*;
import com.example.openapi.sdk.api.GreetingsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        GreetingsApi apiInstance = new GreetingsApi(defaultClient);
        String name = "name_example"; // String | Nome da pessoa
        String title = "title_example"; // String | Título opcional (Dr., Ms., etc)
        try {
            GreetingResponse result = apiInstance.getGreeting(name, title);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GreetingsApi#getGreeting");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**| Nome da pessoa | |
| **title** | **String**| Título opcional (Dr., Ms., etc) | [optional] |

### Return type

[**GreetingResponse**](GreetingResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Saudação calculada |  -  |
| **404** | Não encontrado |  -  |

