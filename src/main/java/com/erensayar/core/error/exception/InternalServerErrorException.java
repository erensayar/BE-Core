package com.erensayar.core.error.exception;

import static com.erensayar.core.error.exception.BaseExceptionConstants.INTERNAL_SERVER_ERROR_CODE;
import static com.erensayar.core.error.exception.BaseExceptionConstants.INTERNAL_SERVER_ERROR_MESSAGE;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class InternalServerErrorException extends BaseException {

  private static final String ERROR_CODE = INTERNAL_SERVER_ERROR_CODE;
  private static final String ERROR_MESSAGE = INTERNAL_SERVER_ERROR_MESSAGE;

  public InternalServerErrorException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public InternalServerErrorException(final String errCode, final String errorMessage, final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public InternalServerErrorException(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public InternalServerErrorException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public InternalServerErrorException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }

}
