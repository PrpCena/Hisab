package com.prp.Hisab.account.infrastructure;

import com.prp.Hisab.account.domain.AccountStatus;
import com.prp.Hisab.account.domain.AccountType;
import java.util.UUID;

public interface ListAccountProjection {
  UUID getId();

  AccountType getType();

  AccountStatus getStatus();
}
