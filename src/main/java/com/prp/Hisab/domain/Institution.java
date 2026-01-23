package com.prp.Hisab.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Institution {
  private final UUID id;
  private final User createdBy;
  private final Instant created;
  
  private String name;
  private List<Account> accounts;
  
  public List<Account> getAccounts() {
	return Collections.unmodifiableList(accounts);
  }
  
}
