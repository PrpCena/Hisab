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
}
