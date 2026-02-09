package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;
import com.prp.Hisab.domain.dto.response.ListAccountResponse;

import java.util.UUID;

public interface AccountService {
  CreateAccountResponse createAccount(CreateAccountRequest request);
    
    ListAccountResponse listAccounts(UUID institutionId);
}
