package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Account;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;
import com.prp.Hisab.domain.dto.response.ListAccountResponse;
import com.prp.Hisab.domain.entity.AccountEntity;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.exception.ResourceNotFoundException;
import com.prp.Hisab.mapper.AccountMapper;
import com.prp.Hisab.repository.AccountRepository;
import com.prp.Hisab.repository.InstitutionRepository;
import com.prp.Hisab.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

  private final UserContext userContext;
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final InstitutionRepository institutionRepository;

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public CreateAccountResponse createAccount(CreateAccountRequest request) {
    User user = userContext.getCurrentUser();

    institutionRepository
        .findByIdAndCreatedById(request.institutionId(), user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Invalid institution to create account"));

    Account account =
        Account.builder().type(request.accountType()).status(AccountStatus.OPEN).build();

    AccountEntity accountEntity = accountMapper.toEntity(account);

    accountEntity.setInstitution(
        entityManager.getReference(InstitutionEntity.class, request.institutionId()));

    accountEntity = accountRepository.save(accountEntity);

    return new CreateAccountResponse(
        accountEntity.getId(), accountEntity.getType(), accountEntity.getStatus());
  }

  @Override
  @Transactional(readOnly = true)
  public ListAccountResponse listAccounts(UUID institutionId) {
    User user = userContext.getCurrentUser();

    institutionRepository
        .findByIdAndCreatedById(institutionId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Accounts not found"));

    List<CreateAccountResponse> accountDtos =
        accountRepository.findAllByInstitutionId(institutionId).stream()
            .map(
                projection ->
                    new CreateAccountResponse(
                        projection.getId(), projection.getType(), projection.getStatus()))
            .toList();

    return new ListAccountResponse(accountDtos);
  }

  @Override
  @Transactional
  public void deleteAccount(UUID accountId) {
    User user = userContext.getCurrentUser();

    AccountEntity accountEntity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    accountRepository.delete(accountEntity);
  }

  @Override
  @Transactional
  public void transferAccount(UUID accountId, TransferAccountRequest request) {
    User user = userContext.getCurrentUser();

    AccountEntity entity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Account domain = accountMapper.toDomain(entity);

    domain.transferAccount(request.institution());

    InstitutionEntity newInstitution =
        institutionRepository
            .findByIdAndCreatedById(request.institution(), user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

    entity.setInstitution(newInstitution);
  }

  @Override
  @Transactional
  public void changeType(UUID accountId, ChangeAccountTypeRequest request) {
    User user = userContext.getCurrentUser();

    AccountEntity entity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Account domain = accountMapper.toDomain(entity);

    domain.changeType(request.type());

    accountMapper.updateEntity(domain, entity);
  }
}
