package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.ChangeAccountStatusRequest;
import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.AccountResponse;
import com.prp.Hisab.service.AccountService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
  private final AccountService accountService;

  @PostMapping
  ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
    AccountResponse response = accountService.createAccount(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/{institutionId}")
  ResponseEntity<List<AccountResponse>> listAccount(@PathVariable UUID institutionId) {
    List<AccountResponse> response = accountService.listAccounts(institutionId);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{accountId}")
  ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId) {
    accountService.deleteAccount(accountId);

    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{accountId}/transfer")
  ResponseEntity<AccountResponse> transferAccount(
      @PathVariable UUID accountId, @Valid @RequestBody TransferAccountRequest request) {
    AccountResponse response = accountService.transferAccount(accountId, request);
    return ResponseEntity.ok(response);
  }

  @PutMapping(path = "/{accountId}/changeType")
  ResponseEntity<AccountResponse> changeType(
      @PathVariable UUID accountId, @Valid @RequestBody ChangeAccountTypeRequest request) {
    AccountResponse response = accountService.changeType(accountId, request);
    return ResponseEntity.ok(response);
  }

  @PutMapping(path = "/{accountId}/changeStatus")
  ResponseEntity<AccountResponse> changeType(
      @PathVariable UUID accountId, @Valid @RequestBody ChangeAccountStatusRequest request) {
    AccountResponse response = accountService.changeStatus(accountId, request);
    return ResponseEntity.ok(response);
  }
}
