package com.prp.Hisab.tag.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTagRequest(
    @NotNull @NotBlank(message = ERROR_MESSAGE_NAME_NOT_BLANK) String name) {
  public static final String ERROR_MESSAGE_NAME_NOT_BLANK = "Name is required";
}
