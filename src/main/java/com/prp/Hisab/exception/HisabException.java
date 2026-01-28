package com.prp.Hisab.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class HisabException
  extends RuntimeException {
  private final HttpStatus status;
  
  
  protected HisabException(String message, HttpStatus unauthorized) {
	super(message);
	this.status = status;
  }
}
