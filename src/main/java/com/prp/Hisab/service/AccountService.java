package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.ChangeAccountStatusRequest;
import com.prp.Hisab.domain.dto.request.ChangeAccountTypeRequest;
import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.request.TransferAccountRequest;
import com.prp.Hisab.domain.dto.response.AccountResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface AccountService {
  AccountResponse createAccount(CreateAccountRequest request);

  List<AccountResponse> listAccounts(UUID institutionId);

  void deleteAccount(UUID accountId);

  AccountResponse transferAccount(UUID accountId, @Valid TransferAccountRequest request);

  AccountResponse changeType(UUID accountId, @Valid ChangeAccountTypeRequest request);

  AccountResponse changeStatus(UUID accountId, @Valid ChangeAccountStatusRequest request);
}
