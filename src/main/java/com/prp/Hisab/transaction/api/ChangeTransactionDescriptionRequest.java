package com.prp.Hisab.transaction.api;

import static com.prp.Hisab.transaction.api.CreateTransactionRequest.ERROR_MESSAGE_NO_DESCRIPTION;

import jakarta.validation.constraints.NotNull;

public record ChangeTransactionDescriptionRequest(
    @NotNull(message = ERROR_MESSAGE_NO_DESCRIPTION) String description) {}
