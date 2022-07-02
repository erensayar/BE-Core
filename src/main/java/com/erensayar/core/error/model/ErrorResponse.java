package com.erensayar.core.error.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

  private String errorCode;

  private String errorMessage;

  private JavaException exception;

  @Builder.Default
  private LocalDateTime timeStamp = LocalDateTime.now();

  @Getter
  @Setter
  @AllArgsConstructor
  public static class JavaException {
    private String exceptionMessage;
    private String exceptionDescription;
  }

}
