package com.prp.Hisab.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HisabException {
  public ResourceNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
