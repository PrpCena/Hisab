package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.CreateTransactionRequest;
import com.prp.Hisab.domain.dto.response.CreateTransactionResponse;
import com.prp.Hisab.domain.dto.response.ListTransactionResponse;
import com.prp.Hisab.service.TransactionService;
import jakarta.validation.Valid;
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
  ResponseEntity<CreateTransactionResponse> createTransaction(
      @PathVariable UUID accountId, @Valid @RequestBody CreateTransactionRequest request) {
    CreateTransactionResponse response = transactionService.createTransaction(accountId, request);

    return ResponseEntity.ok(response);
  }

  @GetMapping(path = "/{accountId}")
  ResponseEntity<ListTransactionResponse> listTransactions(@PathVariable UUID accountId) {
    ListTransactionResponse response = transactionService.listTransaction(accountId);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping(path = "/{transactionId}")
  ResponseEntity<Void> deleteTransaction(@PathVariable UUID transactionId) {
    transactionService.deleteTransaction(transactionId);

    return ResponseEntity.ok().build();
  }
}
