package com.prp.Hisab.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Transaction {
  private final UUID id;
  private String description;
  private BigDecimal amount;
  private Account account;
  private LocalDate date;
  private List<TransactionAllocation> transactionAllocations;
  
  public List<TransactionAllocation> getTransactionAllocations() {
    return Collections.unmodifiableList(transactionAllocations);
  }
  
}
