package com.prp.Hisab.account.application;

import com.prp.Hisab.account.api.ChangeAccountStatusRequest;
import com.prp.Hisab.account.api.ChangeAccountTypeRequest;
import com.prp.Hisab.account.api.CreateAccountRequest;
import com.prp.Hisab.account.api.TransferAccountRequest;
import com.prp.Hisab.account.api.AccountResponse;
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
