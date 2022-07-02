package com.erensayar.core.error.exception;

import static com.erensayar.core.error.exception.BaseExceptionConstants.CONFLICT_ERROR_CODE;
import static com.erensayar.core.error.exception.BaseExceptionConstants.CONFLICT_ERROR_MESSAGE;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class ConflictException extends BaseException {

  private static final String ERROR_CODE = CONFLICT_ERROR_CODE;
  private static final String ERROR_MESSAGE = CONFLICT_ERROR_MESSAGE;

  public ConflictException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public ConflictException(final String errCode, final String errorMessage, final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public ConflictException(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public ConflictException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public ConflictException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }

}
