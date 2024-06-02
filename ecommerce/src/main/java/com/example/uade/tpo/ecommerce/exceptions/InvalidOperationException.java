package com.example.uade.tpo.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidOperationException extends Exception {
  public InvalidOperationException(String message) {
    super("InvalidOperationException: " + message);
  }
}
