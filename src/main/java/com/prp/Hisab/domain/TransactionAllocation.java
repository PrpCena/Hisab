package com.prp.Hisab.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class TransactionAllocation {
  private final UUID id;
  private Transaction transaction;
  private Tag tag;
  private BigDecimal amount;
  
}
