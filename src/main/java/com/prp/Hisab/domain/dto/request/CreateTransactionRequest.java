package com.prp.Hisab.domain.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(
    @NotNull(message = ERROR_MESSAGE_NO_DESCRIPTION) String description,
    @NotNull(message = ERROR_MESSAGE_ZERO_AMOUNT) @Digits(integer = 19, fraction = 4)
        BigDecimal amount,
    @NotNull(message = ERROR_MESSAGE_NO_DATE) LocalDate date) {
  public static final String ERROR_MESSAGE_NO_DESCRIPTION = "Description required";
  public static final String ERROR_MESSAGE_ZERO_AMOUNT = "Amount can not be zero";
  public static final String ERROR_MESSAGE_NO_DATE = "Date required";
}
