package com.prp.Hisab.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TransferAccountRequest(@NotNull(message = ERROR_MESSAGE_INVALID_INSTITUTION) UUID institution) {
  public static final String ERROR_MESSAGE_INVALID_INSTITUTION = "Must be a valid institution";
}
