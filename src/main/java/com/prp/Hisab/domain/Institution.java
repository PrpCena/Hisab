package com.prp.Hisab.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Institution {
  UUID id;
  User createdBy;
  Instant created;
  
  String name;
  List<Account> accounts;
  
  public List<Account> getAccounts() {
	return accounts == null ? Collections.emptyList() : Collections.unmodifiableList(accounts);
  }
  
}
