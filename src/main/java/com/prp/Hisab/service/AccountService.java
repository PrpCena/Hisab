package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;
import com.prp.Hisab.domain.dto.response.ListAccountResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface AccountService {
  CreateAccountResponse createAccount(CreateAccountRequest request);

  ListAccountResponse listAccounts(UUID institutionId);

  void deleteAccount(UUID accountId);
  
  void transferAccount(UUID accountId, @Valid TransferAccountRequest request);
  
  void changeType(UUID accountId, @Valid ChangeAccountTypeRequest request);
}
