package com.prp.Hisab.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class User {
  UUID id;
  String keyCloackId;
  Instant created;
  String name;
  String email;
  List<Institution> institutions;
  
  public List<Institution> getInstitutions() {
	return institutions == null ? Collections.emptyList() : Collections.unmodifiableList(institutions);
  }
  
}
