package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateTransactionRequest;
import com.prp.Hisab.domain.dto.response.CreateTransactionResponse;
import com.prp.Hisab.domain.dto.response.ListTransactionResponse;
import jakarta.validation.Valid;
import java.util.UUID;

public interface TransactionService {
  CreateTransactionResponse createTransaction(
      UUID accountId, @Valid CreateTransactionRequest request);

  ListTransactionResponse listTransaction(UUID accountId);

  void deleteTransaction(UUID transactionId);
}
