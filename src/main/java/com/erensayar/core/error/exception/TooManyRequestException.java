package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class TooManyRequestException extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.TOO_MANY_REQUEST_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.TOO_MANY_REQUEST_MESSAGE;

  public TooManyRequestException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public TooManyRequestException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public TooManyRequestException(final ApiError apiError) {
    super(apiError.getErrorCode().toString(), apiError.getDescription(), LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), apiError.getDescription()))
        .build());
  }

  public TooManyRequestException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }
}
