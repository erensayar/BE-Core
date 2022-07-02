package com.erensayar.core.error.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Business errors
 */
@Getter
@Setter
@ToString
public class ApiError {

  private Integer errorCode;
  private String description;

  private ApiError() {
  }

  public static ApiError of(Integer code, String description) {
    ApiError apiError = new ApiError();
    apiError.setErrorCode(code);
    apiError.setDescription(description);
    return apiError;
  }

}
