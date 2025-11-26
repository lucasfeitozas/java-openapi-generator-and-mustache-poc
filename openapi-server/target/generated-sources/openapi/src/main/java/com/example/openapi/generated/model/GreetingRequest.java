package com.example.openapi.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GreetingRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.5.0")
public class GreetingRequest {

  private String message;

  private String language;

  public GreetingRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GreetingRequest(String message) {
    this.message = message;
  }

  public GreetingRequest message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @NotNull 
  @Schema(name = "message", example = "Olá, seja bem-vindo!", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GreetingRequest language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Locale BCP 47
   * @return language
  */
  
  @Schema(name = "language", example = "pt-BR", description = "Locale BCP 47", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("language")
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GreetingRequest greetingRequest = (GreetingRequest) o;
    return Objects.equals(this.message, greetingRequest.message) &&
        Objects.equals(this.language, greetingRequest.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GreetingRequest {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

