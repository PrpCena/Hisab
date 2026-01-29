package com.prp.Hisab.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Value
public class Tag {
  UUID id;
  Instant created;
  String name;
  Set<Tag> parents;
  Set<Tag> children;
  List<TransactionAllocation> transactionAllocations;
  
  public Set<Tag> getParents() {
	return parents == null ? Collections.emptySet() : Collections.unmodifiableSet(parents);
  }
  
  public Set<Tag> getChildren() {
	return children == null ? Collections.emptySet() : Collections.unmodifiableSet(children);
  }
  
  public List<TransactionAllocation> getTransactionAllocations() {
	return transactionAllocations == null ? Collections.emptyList() : Collections.unmodifiableList(
	  transactionAllocations);
  }
}
