package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;
import com.prp.Hisab.domain.dto.response.ListAccountResponse;
import com.prp.Hisab.service.AccountService;
import jakarta.validation.Valid;
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
  ResponseEntity<CreateAccountResponse> createAccount(
      @Valid @RequestBody CreateAccountRequest request) {
    CreateAccountResponse response = accountService.createAccount(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/{institutionId}")
  ResponseEntity<ListAccountResponse> listAccount(@PathVariable UUID institutionId) {
    ListAccountResponse response = accountService.listAccounts(institutionId);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{accountId}")
  ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId) {
    accountService.deleteAccount(accountId);

    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{accountId}/transfer")
  ResponseEntity<Void> transferAccount(
      @PathVariable UUID accountId, @Valid @RequestBody TransferAccountRequest request) {
    accountService.transferAccount(accountId, request);
    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{accountId}/changeType")
  ResponseEntity<Void> changeType(
      @PathVariable UUID accountId, @Valid @RequestBody ChangeAccountTypeRequest request) {
    accountService.changeType(accountId, request);
    return ResponseEntity.ok().build();
  }
}
