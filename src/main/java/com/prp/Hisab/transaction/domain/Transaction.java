package com.prp.Hisab.transaction.domain;

import com.prp.Hisab.common.exception.DomainException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transaction {
  private static final int DESCRIPTION_MIN_LENGTH = 3;
  private static final int DESCRIPTION_MAX_LENGTH = 75;
  private String description;
  private BigDecimal amount;
  private UUID accountId;
  private LocalDate date;
  @Builder.Default private Set<UUID> tagIds = new HashSet<>();

  public void accountChange(UUID accountId) {
    if (this.accountId.equals(accountId)) {
      throw new DomainException("Transaction already exists");
    }

    this.accountId = accountId;
  }

  public void changeDescription(String description) {
    description = description.trim().toLowerCase();

    if (description.length() < DESCRIPTION_MIN_LENGTH
        || description.length() > DESCRIPTION_MAX_LENGTH) {
      throw new DomainException(
          "Description length should be between "
              + DESCRIPTION_MIN_LENGTH
              + " and "
              + DESCRIPTION_MAX_LENGTH
              + " characters");
    }

    this.description = description;
  }
}
