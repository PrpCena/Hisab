package com.prp.Hisab.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Transaction {
  UUID id;
  String description;
  BigDecimal amount;
  Account account;
  LocalDate date;
  List<TransactionAllocation> transactionAllocations;
  
  public List<TransactionAllocation> getTransactionAllocations() {
	return transactionAllocations == null ? Collections.emptyList() : Collections.unmodifiableList(
	  transactionAllocations);
  }
  
}
