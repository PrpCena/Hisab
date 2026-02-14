package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.ChangeTransactionAccountRequest;
import com.prp.Hisab.domain.dto.request.CreateTransactionRequest;
import com.prp.Hisab.domain.dto.response.TransactionResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
  TransactionResponse createTransaction(UUID accountId, @Valid CreateTransactionRequest request);

  List<TransactionResponse> listTransaction(UUID accountId);

  void deleteTransaction(UUID transactionId);

  TransactionResponse accountChange(
      UUID transactionId, @Valid ChangeTransactionAccountRequest request);
}
