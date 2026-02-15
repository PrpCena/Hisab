package com.prp.Hisab.tag.domain;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Tag {
  UUID id;
  Instant created;
  String name;
}
