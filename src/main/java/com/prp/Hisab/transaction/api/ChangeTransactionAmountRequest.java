package com.prp.Hisab.transaction.api;

import static com.prp.Hisab.transaction.api.CreateTransactionRequest.ERROR_MESSAGE_ZERO_AMOUNT;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ChangeTransactionAmountRequest(
    @NotNull(message = ERROR_MESSAGE_ZERO_AMOUNT) BigDecimal amount) {}
