package com.prp.Hisab.domain;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
  UUID id;
  String keyCloackId;
  Instant created;
  String name;
  String email;
}
