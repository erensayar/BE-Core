package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class BadRequestException extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.BAD_REQUEST_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.BAD_REQUEST_ERROR_MESSAGE;

  public BadRequestException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public BadRequestException(final String errCode, final String errorMessage, final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public BadRequestException(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public BadRequestException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public BadRequestException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }

}
