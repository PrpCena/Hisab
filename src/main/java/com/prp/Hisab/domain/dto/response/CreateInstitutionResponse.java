package com.prp.Hisab.domain.dto.response;

import java.util.UUID;

public record CreateInstitutionResponse(
        UUID id,
        String name
) {}
