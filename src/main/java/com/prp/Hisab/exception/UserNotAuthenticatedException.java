package com.prp.Hisab.exception;

import org.springframework.http.HttpStatus;

public class UserNotAuthenticatedException
  extends HisabException {
  public UserNotAuthenticatedException(String message) {
	super(message, HttpStatus.UNAUTHORIZED);
  }
}
