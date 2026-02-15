package com.prp.Hisab.institution.domain;

import com.prp.Hisab.common.exception.DomainException;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Institution {

  public static final int MAX_NAME_LENGTH = 100;
  public static final int MIN_NAME_LENGTH = 1;
  public static final String NAME_LENGTH_INVARIANT =
      "Institution name length must be between" + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH;

  private final UUID id;
  private String name;

  public void rename(String newName) {
    if (newName == null || newName.isEmpty() || newName.length() > MAX_NAME_LENGTH) {
      throw new DomainException(NAME_LENGTH_INVARIANT);
    }
    newName = newName.trim().toLowerCase();
    
    this.name = newName;
  }
}
