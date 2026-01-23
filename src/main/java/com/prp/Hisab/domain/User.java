package com.prp.Hisab.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class User {
  private final UUID id;
  private final String keyCloackId;
  private final Instant created;
  private String name;
  private String email;
  private List<Institution> institutions;
  
  public List<Institution> getInstitutions() {
	return Collections.unmodifiableList(institutions);
  }
  
}
