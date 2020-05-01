package com.oneup.tvseries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private static String MESSAGE = "Resource %s not found with %s";

  public ResourceNotFoundException(String resource, String id) {
    super(String.format(MESSAGE, resource, id));
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

}
