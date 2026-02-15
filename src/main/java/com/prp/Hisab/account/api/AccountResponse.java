package com.prp.Hisab.account.api;

import com.prp.Hisab.account.domain.AccountStatus;
import com.prp.Hisab.account.domain.AccountType;
import java.util.UUID;

public record AccountResponse(
    UUID id, UUID institutionId, AccountType accountType, AccountStatus accountStatus) {}
