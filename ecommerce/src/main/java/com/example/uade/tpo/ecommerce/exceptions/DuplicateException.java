package com.example.uade.tpo.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DuplicateException extends Exception {
  public DuplicateException(String message) {
    super("DuplicateException: " + message);
  }
}
