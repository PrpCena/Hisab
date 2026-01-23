package com.prp.Hisab.domain;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Account {
  private final UUID id;
  private final Instant created;
  
  private AccountStatus status;
  private AccountType type;
  private Institution institution;
  private List<Transaction> transactions;
  
  public List<Transaction> getTransactions() {
	return Collections.unmodifiableList(transactions);
  }
  
}
