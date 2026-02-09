package com.prp.Hisab.domain.dto.response;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import java.util.UUID;

public record CreateAccountResponse(
    UUID id,
    AccountType accountType,
    AccountStatus accountStatus) {}
