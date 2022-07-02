package com.erensayar.core.error.exception;

import com.erensayar.core.log.LogModel;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private final String errorCode;
  private final String errorMessage;
  private final LogModel logModel;

  public BaseException() {
    this.errorCode = BaseExceptionConstants.ERROR_CODE;
    this.errorMessage = BaseExceptionConstants.ERROR_MESSAGE;
    this.logModel = null;
  }

  public BaseException(final String errCode, final String errorMessage, final LogModel logModel) {
    this.errorCode = errCode;
    this.errorMessage = errorMessage;
    this.logModel = logModel;
  }

  public BaseException(final String errCode, final String errorMessage) {
    this.errorCode = errCode;
    this.errorMessage = errorMessage;
    this.logModel = null;
  }

  public BaseException(final String errorMessage, final LogModel logModel) {
    this.errorCode = BaseExceptionConstants.ERROR_CODE;
    this.errorMessage = errorMessage;
    this.logModel = logModel;
  }

  public BaseException(final String errorMessage) {
    this.errorCode = BaseExceptionConstants.ERROR_CODE;
    this.errorMessage = errorMessage;
    this.logModel = null;
  }

}
