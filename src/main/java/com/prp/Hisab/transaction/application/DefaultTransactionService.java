package com.prp.Hisab.transaction.application;

import com.prp.Hisab.account.domain.Account;
import com.prp.Hisab.transaction.domain.Transaction;
import com.prp.Hisab.user.domain.User;
import com.prp.Hisab.transaction.api.ChangeTransactionAccountRequest;
import com.prp.Hisab.transaction.api.CreateTransactionRequest;
import com.prp.Hisab.transaction.api.TransactionResponse;
import com.prp.Hisab.account.infrastructure.AccountEntity;
import com.prp.Hisab.transaction.infrastructure.TransactionEntity;
import com.prp.Hisab.common.exception.ResourceNotFoundException;
import com.prp.Hisab.account.infrastructure.AccountMapper;
import com.prp.Hisab.transaction.infrastructure.TransactionMapper;
import com.prp.Hisab.account.infrastructure.AccountRepository;
import com.prp.Hisab.transaction.infrastructure.TransactionRepository;
import com.prp.Hisab.user.application.UserContext;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTransactionService implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;
  private final AccountRepository accountRepository;
  private final UserContext userContext;
  private final AccountMapper accountMapper;

  @Override
  @Transactional
  public TransactionResponse createTransaction(UUID accountId, CreateTransactionRequest request) {
    User user = userContext.getCurrentUser();

    AccountEntity accountEntity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Transaction transaction =
        Transaction.builder()
            .date(request.date())
            .amount(request.amount())
            .description(request.description())
            .build();

    TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);

    transactionEntity.setAccount(accountEntity);

    transactionEntity = transactionRepository.save(transactionEntity);

    return new TransactionResponse(
        transactionEntity.getId(),
        transactionEntity.getAmount(),
        transactionEntity.getDate(),
        transactionEntity.getDescription(),
        accountEntity.getId());
  }

  @Override
  @Transactional(readOnly = true)
  public List<TransactionResponse> listTransaction(UUID accountId) {
    User user = userContext.getCurrentUser();

    accountRepository
        .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    return transactionRepository.findAllByAccountId(accountId).stream()
        .map(
            projection ->
                new TransactionResponse(
                    projection.getId(),
                    projection.getAmount(),
                    projection.getDate(),
                    projection.getDescription(),
                    accountId))
        .toList();
  }

  @Override
  @Transactional
  public void deleteTransaction(UUID transactionId) {
    User user = userContext.getCurrentUser();

    TransactionEntity transactionEntity =
        transactionRepository
            .findByIdAndAccount_Institution_CreatedBy_Id(transactionId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

    transactionRepository.delete(transactionEntity);
  }

  @Override
  @Transactional
  public TransactionResponse accountChange(
      UUID transactionId, @Valid ChangeTransactionAccountRequest request) {
    User user = userContext.getCurrentUser();

    TransactionEntity transactionEntity =
        transactionRepository
            .findByIdAndAccount_Institution_CreatedBy_Id(transactionId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

    AccountEntity accountEntity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(request.accountId(), user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Transaction transaction = transactionMapper.toDomain(transactionEntity);
    Account account = accountMapper.toDomain(accountEntity);

    account.isActive();

    transaction.accountChange(request.accountId());

    transactionEntity.setAccount(accountEntity);

    transactionEntity = transactionRepository.save(transactionEntity);

    return new TransactionResponse(
        transactionEntity.getId(),
        transactionEntity.getAmount(),
        transactionEntity.getDate(),
        transactionEntity.getDescription(),
        accountEntity.getId());
  }
}
