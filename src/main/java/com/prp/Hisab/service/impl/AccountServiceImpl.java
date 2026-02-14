package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Account;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.ChangeAccountStatusRequest;
import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.AccountResponse;
import com.prp.Hisab.domain.entity.AccountEntity;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.exception.ResourceNotFoundException;
import com.prp.Hisab.mapper.AccountMapper;
import com.prp.Hisab.repository.AccountRepository;
import com.prp.Hisab.repository.InstitutionRepository;
import com.prp.Hisab.service.AccountService;
import com.prp.Hisab.service.UserContext;
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

  @Override
  @Transactional
  public AccountResponse createAccount(CreateAccountRequest request) {
    User user = userContext.getCurrentUser();

    InstitutionEntity institution =
        institutionRepository
            .findByIdAndCreatedById(request.institutionId(), user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Invalid institution to create account"));

    Account account =
        Account.builder().type(request.accountType()).status(AccountStatus.OPEN).build();

    AccountEntity accountEntity = accountMapper.toEntity(account);

    accountEntity.setInstitution(institution);

    accountEntity = accountRepository.save(accountEntity);

    return new AccountResponse(
        accountEntity.getId(),
        request.institutionId(),
        accountEntity.getType(),
        accountEntity.getStatus());
  }

  @Override
  @Transactional(readOnly = true)
  public List<AccountResponse> listAccounts(UUID institutionId) {
    User user = userContext.getCurrentUser();

    return accountRepository
        .findAllByInstitution_IdAndInstitution_CreatedBy_Id(institutionId, user.getId())
        .stream()
        .map(
            projection ->
                new AccountResponse(
                    projection.getId(),
                    institutionId,
                    projection.getType(),
                    projection.getStatus()))
        .toList();
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
  public AccountResponse transferAccount(UUID accountId, TransferAccountRequest request) {
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

    entity = accountRepository.save(entity);

    return new AccountResponse(
        entity.getId(), newInstitution.getId(), entity.getType(), entity.getStatus());
  }

  @Override
  @Transactional
  public AccountResponse changeType(UUID accountId, ChangeAccountTypeRequest request) {
    User user = userContext.getCurrentUser();

    AccountEntity entity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Account domain = accountMapper.toDomain(entity);

    domain.changeType(request.type());

    accountMapper.updateEntity(domain, entity);

    entity = accountRepository.save(entity);

    return new AccountResponse(
        entity.getId(), domain.getInstitution(), entity.getType(), entity.getStatus());
  }

  @Override
  @Transactional
  public AccountResponse changeStatus(UUID accountId, ChangeAccountStatusRequest request) {
    User user = userContext.getCurrentUser();

    AccountEntity entity =
        accountRepository
            .findByIdAndInstitution_CreatedBy_Id(accountId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

    Account domain = accountMapper.toDomain(entity);

    domain.changeStatus(request.status());

    accountMapper.updateEntity(domain, entity);

    entity = accountRepository.save(entity);

    return new AccountResponse(
        entity.getId(), domain.getInstitution(), entity.getType(), entity.getStatus());
  }
}
