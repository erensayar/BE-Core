package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class NotFoundException extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.NOT_FOUND_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.NOT_FOUND_ERROR_MESSAGE;

  public NotFoundException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public NotFoundException(final String errCode, final String errorMessage,
      final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public NotFoundException(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public NotFoundException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(),
        logModel);
  }

  public NotFoundException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }

}
