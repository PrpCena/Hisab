package com.prp.Hisab.exception;

import org.springframework.http.HttpStatus;

public class DomainException extends HisabException {
  public DomainException(String message) {
    super(message, HttpStatus.UNPROCESSABLE_CONTENT);
  }
}
