package com.erensayar.core.error.exception;

import static com.erensayar.core.error.exception.BaseExceptionConstants.FORBIDDEN_ERROR_CODE;
import static com.erensayar.core.error.exception.BaseExceptionConstants.FORBIDDEN_ERROR_MESSAGE;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class ForbiddenException extends BaseException {

  private static final String ERROR_CODE = FORBIDDEN_ERROR_CODE;
  private static final String ERROR_MESSAGE = FORBIDDEN_ERROR_MESSAGE;

  public ForbiddenException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public ForbiddenException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public ForbiddenException(final ApiError apiError) {
    super(apiError.getErrorCode().toString(), apiError.getDescription(), LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), apiError.getDescription()))
        .build());
  }

  public ForbiddenException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }
}