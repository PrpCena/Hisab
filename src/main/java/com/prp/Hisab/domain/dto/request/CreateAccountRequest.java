package com.prp.Hisab.domain.dto.request;

import com.prp.Hisab.domain.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateAccountRequest(
    @NotNull(message = ERROR_MESSAGE_INVALID_INSTITUTION) UUID institutionId,
    @NotNull(message = ERROR_MESSAGE_NULL_ACCOUNT_TYPE) AccountType accountType) {
  public static final String ERROR_MESSAGE_INVALID_INSTITUTION = "Must be a valid institution";
  public static final String ERROR_MESSAGE_NULL_ACCOUNT_TYPE = "Account type must not be null";
}
