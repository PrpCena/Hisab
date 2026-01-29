package com.prp.Hisab.domain;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Account {
  UUID id;
  Instant created;
  AccountStatus status;
  AccountType type;
  Institution institution;
  List<Transaction> transactions;
  
  public List<Transaction> getTransactions() {
	return transactions == null ? Collections.emptyList() : Collections.unmodifiableList(transactions);
  }
  
}
