package com.erensayar.core.error.exception;

import static com.erensayar.core.error.exception.BaseExceptionConstants.UNAUTHORIZED_ERROR_CODE;
import static com.erensayar.core.error.exception.BaseExceptionConstants.UNAUTHORIZED_ERROR_MESSAGE;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class UnauthorizedException extends BaseException {

  private static final String ERROR_CODE = UNAUTHORIZED_ERROR_CODE;
  private static final String ERROR_MESSAGE = UNAUTHORIZED_ERROR_MESSAGE;

  public UnauthorizedException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public UnauthorizedException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public UnauthorizedException(final ApiError apiError) {
    super(apiError.getErrorCode().toString(), apiError.getDescription(), LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), apiError.getDescription()))
        .build());
  }

  public UnauthorizedException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }
}
