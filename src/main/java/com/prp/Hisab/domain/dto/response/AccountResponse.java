package com.prp.Hisab.domain.dto.response;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import java.util.UUID;

public record AccountResponse(
    UUID id, UUID institutionId, AccountType accountType, AccountStatus accountStatus) {}
