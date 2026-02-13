package com.prp.Hisab.domain.dto.request;

import com.prp.Hisab.domain.enums.AccountType;
import jakarta.validation.constraints.NotNull;

public record ChangeAccountTypeRequest(
    @NotNull(message = ERROR_MESSAGE_NULL_ACCOUNT_TYPE) AccountType type) {
  public static final String ERROR_MESSAGE_NULL_ACCOUNT_TYPE = "Account type must not be null";
}
