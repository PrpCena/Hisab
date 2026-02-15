package com.prp.Hisab.institution.api;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateInstitutionRequest(
    @NotBlank(message = ERROR_MESSAGE_NAME_NOT_BLANK)
        @Length(max = MAXIMUM_NAME_LENGTH, message = ERROR_MESSAGE_NAME_LENGTH)
        String name) {
  public static final int MAXIMUM_NAME_LENGTH = 100;
  public static final String ERROR_MESSAGE_NAME_NOT_BLANK = "Institution name is required";
  public static final String ERROR_MESSAGE_NAME_LENGTH =
      "Institution name must be between 1 and 100 characters";
}
