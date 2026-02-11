package com.prp.Hisab.domain.dto.response;

import java.util.List;

public record ListTransactionResponse(List<CreateTransactionResponse> transactions) {}
