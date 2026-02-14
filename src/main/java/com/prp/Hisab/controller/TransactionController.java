package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.ChangeTransactionAccountRequest;
import com.prp.Hisab.domain.dto.request.CreateTransactionRequest;
import com.prp.Hisab.domain.dto.response.TransactionResponse;
import com.prp.Hisab.service.TransactionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping(path = "/{accountId}")
  ResponseEntity<TransactionResponse> createTransaction(
      @PathVariable UUID accountId, @Valid @RequestBody CreateTransactionRequest request) {
    TransactionResponse response = transactionService.createTransaction(accountId, request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/{accountId}")
  ResponseEntity<List<TransactionResponse>> listTransactions(@PathVariable UUID accountId) {
    List<TransactionResponse> response = transactionService.listTransaction(accountId);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping(path = "/{transactionId}")
  ResponseEntity<Void> deleteTransaction(@PathVariable UUID transactionId) {
    transactionService.deleteTransaction(transactionId);

    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{transactionId}/accountChange")
  ResponseEntity<TransactionResponse> changeAccount(
      @PathVariable UUID transactionId,
      @Valid @RequestBody ChangeTransactionAccountRequest request) {
    TransactionResponse response = transactionService.accountChange(transactionId, request);
    return ResponseEntity.ok(response);
  }
}
