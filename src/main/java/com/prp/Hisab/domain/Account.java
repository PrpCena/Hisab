package com.prp.Hisab.domain;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Account {
  UUID id;
  Instant created;
  AccountStatus status;
  AccountType type;
  UUID institutionId;
}
