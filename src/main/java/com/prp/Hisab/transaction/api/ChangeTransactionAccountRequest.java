package com.prp.Hisab.transaction.api;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ChangeTransactionAccountRequest(
    @NotNull(message = ERROR_MESSAGE_NULL_ACCOUNT) UUID accountId) {
  public static final String ERROR_MESSAGE_NULL_ACCOUNT = "Account ID must be provided";
}
