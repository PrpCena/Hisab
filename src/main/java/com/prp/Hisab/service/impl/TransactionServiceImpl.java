package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Transaction;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.CreateTransactionRequest;
import com.prp.Hisab.domain.dto.response.CreateTransactionResponse;
import com.prp.Hisab.domain.dto.response.ListTransactionResponse;
import com.prp.Hisab.domain.entity.AccountEntity;
import com.prp.Hisab.domain.entity.TransactionEntity;
import com.prp.Hisab.exception.ResourceNotFoundException;
import com.prp.Hisab.mapper.TransactionMapper;
import com.prp.Hisab.repository.AccountRepository;
import com.prp.Hisab.repository.TransactionRepository;
import com.prp.Hisab.service.TransactionService;
import com.prp.Hisab.service.UserContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;
  private final AccountRepository accountRepository;
  private final UserContext userContext;

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public CreateTransactionResponse createTransaction(
      UUID accountId, CreateTransactionRequest request) {
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

    return new CreateTransactionResponse(
        transactionEntity.getId(),
        transactionEntity.getAmount(),
        transactionEntity.getDate(),
        transactionEntity.getDescription());
  }

  @Override
  @Transactional(readOnly = true)
  public ListTransactionResponse listTransaction(UUID accountId) {
    User user = userContext.getCurrentUser();

    accountRepository
        .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    List<CreateTransactionResponse> transactions =
        transactionRepository.findAllByAccountId(accountId).stream()
            .map(
                projection ->
                    new CreateTransactionResponse(
                        projection.getId(),
                        projection.getAmount(),
                        projection.getDate(),
                        projection.getDescription()))
            .toList();

    return new ListTransactionResponse(transactions);
  }
}
