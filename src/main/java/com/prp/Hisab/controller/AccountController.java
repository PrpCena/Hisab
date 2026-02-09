package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;
import com.prp.Hisab.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
  private final AccountService accountService;

  @PostMapping
  ResponseEntity<CreateAccountResponse> createAccount(
      @Valid @RequestBody CreateAccountRequest request) {
    CreateAccountResponse response = accountService.createAccount(request);

    return ResponseEntity.ok(response);
  }
}
