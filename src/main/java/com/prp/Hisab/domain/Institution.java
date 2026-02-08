package com.prp.Hisab.domain;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Institution {
    UUID id;
    UUID createdById;
    Instant created;
    String name;
}
