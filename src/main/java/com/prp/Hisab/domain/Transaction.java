package com.prp.Hisab.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Transaction {
  UUID id;
  String description;
  BigDecimal amount;
  UUID accountId;
  LocalDate date;
  Set<UUID> tagIds = new HashSet<>();

  public void addTag(UUID tagId) {
    tagIds.add(tagId);
  }

  public void removeTag(UUID tagId) {
    tagIds.remove(tagId);
  }

  public Set<UUID> tagIds() {
    return Set.copyOf(tagIds);
  }
}
