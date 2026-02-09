package com.prp.Hisab.repository.projection;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import java.util.UUID;

public interface ListAccountProjection {
  UUID getId();

  AccountType getType();

  AccountStatus getStatus();
}
