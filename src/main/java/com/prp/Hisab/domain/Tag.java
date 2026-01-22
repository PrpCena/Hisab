package com.prp.Hisab.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class Tag {
  private final UUID id;
  private final Instant created;
  private String name;
  private Set<Tag> parents;
  private Set<Tag> children;
  private List<TransactionAllocation> transactionAllocations;
  
  public Set<Tag> getParents() {
    return Collections.unmodifiableSet(parents);
  }
  
  public Set<Tag> getChildren() {
    return Collections.unmodifiableSet(children);
  }
  
  public List<TransactionAllocation> getTransactionAllocations() {
    return Collections.unmodifiableList(transactionAllocations);
  }
}
