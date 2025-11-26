package com.example.openapi.greetings;

import com.example.openapi.generated.api.GreetingsApi;
import com.example.openapi.generated.model.GreetingRequest;
import com.example.openapi.generated.model.GreetingResponse;
import java.time.OffsetDateTime;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsRestController implements GreetingsApi {

    @Override
    public ResponseEntity<GreetingResponse> getGreeting(String name, String title) {
        GreetingResponse response = new GreetingResponse();
        response.setMessage(buildMessage(name, title));
        response.setLanguage(Locale.getDefault().toLanguageTag());
        response.setCreatedAt(OffsetDateTime.now());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GreetingResponse> createGreeting(GreetingRequest greetingRequest) {
        GreetingResponse response = new GreetingResponse();
        response.setMessage(greetingRequest.getMessage());
        if (greetingRequest.getLanguage() != null) {
            response.setLanguage(greetingRequest.getLanguage());
        } else {
            response.setLanguage(Locale.getDefault().toLanguageTag());
        }
        response.setCreatedAt(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private String buildMessage(String name, String title) {
        String prefix = title != null && !title.isBlank() ? title.trim() + " " : "";
        return "Olá " + prefix + name + "!";
    }
}
