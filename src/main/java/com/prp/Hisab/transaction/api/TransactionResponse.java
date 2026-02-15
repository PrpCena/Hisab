package com.prp.Hisab.transaction.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(
    UUID transactionId, BigDecimal amount, LocalDate date, String description, UUID accountId) {}
