package com.prp.Hisab.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
public class TransactionAllocation {
  UUID id;
  Transaction transaction;
  Tag tag;
  BigDecimal amount;
  
}
