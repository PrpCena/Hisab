package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateAccountRequest;
import com.prp.Hisab.domain.dto.response.CreateAccountResponse;

public interface AccountService {
  CreateAccountResponse createAccount(CreateAccountRequest request);
}
