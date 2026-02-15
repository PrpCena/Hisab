package com.prp.Hisab.account.api;

import com.prp.Hisab.account.domain.AccountStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeAccountStatusRequest(@NotNull(message = ERROR_MESSAGE_NULL_ACCOUNT_STATUS) AccountStatus status) {
  public static final String ERROR_MESSAGE_NULL_ACCOUNT_STATUS = "Account status must not be null";
}
