package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class NoContentException extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.NO_CONTENT_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.NO_CONTENT_MESSAGE;

  public NoContentException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public NoContentException(final String errCode, final String errorMessage,
      final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public NoContentException(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public NoContentException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public NoContentException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }

}
