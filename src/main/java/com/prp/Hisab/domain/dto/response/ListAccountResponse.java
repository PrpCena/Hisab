package com.prp.Hisab.domain.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ListAccountResponse(@Valid @NotNull List<CreateAccountResponse> accounts) {}
