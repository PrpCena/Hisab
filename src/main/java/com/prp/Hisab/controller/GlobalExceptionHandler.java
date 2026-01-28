package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.ApiErrorResponse;
import com.prp.Hisab.exception.HisabException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  public ResponseEntity<ApiErrorResponse> handleException(HisabException ex, HttpServletRequest request) {
	ApiErrorResponse errorResponse = new ApiErrorResponse(
	  ex
		.getStatus()
		.value(), ex
					.getStatus()
					.getReasonPhrase(), ex.getMessage(), request.getRequestURI(), Instant.now());
	return new ResponseEntity<>(errorResponse, ex.getStatus());
  }
}
