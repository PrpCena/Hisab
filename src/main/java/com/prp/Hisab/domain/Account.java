package com.prp.Hisab.domain;

import com.prp.Hisab.domain.enums.AccountStatus;
import com.prp.Hisab.domain.enums.AccountType;
import com.prp.Hisab.exception.DomainException;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
  private AccountStatus status;
  private AccountType type;
  private UUID institution;

  public void transferAccount(UUID institution) {

    if (this.institution.equals(institution)) {
      throw new DomainException("You can't transfer to the same institution");
    }

    if (this.status == AccountStatus.CLOSED) {
      throw new DomainException("You can't transfer a closed account");
    }
  }

  public void changeType(AccountType accountType) {

    if (this.type.equals(accountType)) {
      throw new DomainException("You can't change the type to the same type");
    }

    this.type = accountType;
  }

  public void changeStatus(AccountStatus status) {
    if (this.status.equals(status)) {
      throw new DomainException("You can't change the status to the same status");
    }

    this.status = status;
  }

  public void isActive() {
    if (this.status.equals(AccountStatus.CLOSED)) {
      throw new DomainException("Can not make changes to closed account");
    }
  }
}
