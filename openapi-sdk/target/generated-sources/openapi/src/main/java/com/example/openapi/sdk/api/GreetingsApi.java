package com.example.openapi.sdk.api;

import com.example.openapi.sdk.invoker.ApiClient;
import com.example.openapi.sdk.invoker.BaseApi;

import com.example.openapi.sdk.model.GreetingRequest;
import com.example.openapi.sdk.model.GreetingResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.5.0")
public class GreetingsApi extends BaseApi {

    public GreetingsApi() {
        super(new ApiClient());
    }

    public GreetingsApi(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Persiste uma saudação customizada
     * 
     * <p><b>201</b> - Saudação criada
     * <p><b>400</b> - Erro de validação
     * @param greetingRequest  (required)
     * @return GreetingResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GreetingResponse createGreeting(GreetingRequest greetingRequest) throws RestClientException {
        return createGreetingWithHttpInfo(greetingRequest).getBody();
    }

    /**
     * Persiste uma saudação customizada
     * 
     * <p><b>201</b> - Saudação criada
     * <p><b>400</b> - Erro de validação
     * @param greetingRequest  (required)
     * @return ResponseEntity&lt;GreetingResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GreetingResponse> createGreetingWithHttpInfo(GreetingRequest greetingRequest) throws RestClientException {
        Object localVarPostBody = greetingRequest;
        
        // verify the required parameter 'greetingRequest' is set
        if (greetingRequest == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'greetingRequest' when calling createGreeting");
        }
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<GreetingResponse> localReturnType = new ParameterizedTypeReference<GreetingResponse>() {};
        return apiClient.invokeAPI("/greetings", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
    /**
     * Recupera uma saudação personalizada
     * 
     * <p><b>200</b> - Saudação calculada
     * <p><b>404</b> - Não encontrado
     * @param name Nome da pessoa (required)
     * @param title Título opcional (Dr., Ms., etc) (optional)
     * @return GreetingResponse
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public GreetingResponse getGreeting(String name, String title) throws RestClientException {
        return getGreetingWithHttpInfo(name, title).getBody();
    }

    /**
     * Recupera uma saudação personalizada
     * 
     * <p><b>200</b> - Saudação calculada
     * <p><b>404</b> - Não encontrado
     * @param name Nome da pessoa (required)
     * @param title Título opcional (Dr., Ms., etc) (optional)
     * @return ResponseEntity&lt;GreetingResponse&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<GreetingResponse> getGreetingWithHttpInfo(String name, String title) throws RestClientException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling getGreeting");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("name", name);

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "title", title));
        

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<GreetingResponse> localReturnType = new ParameterizedTypeReference<GreetingResponse>() {};
        return apiClient.invokeAPI("/greetings/{name}", HttpMethod.GET, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }

    @Override
    public <T> ResponseEntity<T> invokeAPI(String url, HttpMethod method, Object request, ParameterizedTypeReference<T> returnType) throws RestClientException {
        String localVarPath = url.replace(apiClient.getBasePath(), "");
        Object localVarPostBody = request;

        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        return apiClient.invokeAPI(localVarPath, method, uriVariables, localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
    }
}
