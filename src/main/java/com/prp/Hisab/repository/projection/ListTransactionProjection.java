package com.prp.Hisab.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface ListTransactionProjection {
  UUID getId();

  LocalDate getDate();

  String getDescription();

  BigDecimal getAmount();
}
