package com.prp.Hisab.transaction.application;

import com.prp.Hisab.transaction.api.ChangeTransactionAccountRequest;
import com.prp.Hisab.transaction.api.ChangeTransactionDescriptionRequest;
import com.prp.Hisab.transaction.api.CreateTransactionRequest;
import com.prp.Hisab.transaction.api.TransactionResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
  TransactionResponse createTransaction(UUID accountId, @Valid CreateTransactionRequest request);

  List<TransactionResponse> listTransaction(UUID accountId);

  void deleteTransaction(UUID transactionId);

  TransactionResponse accountChange(
      UUID transactionId, @Valid ChangeTransactionAccountRequest request);

  TransactionResponse changeDescription(
      UUID transactionId, @Valid ChangeTransactionDescriptionRequest request);
}
