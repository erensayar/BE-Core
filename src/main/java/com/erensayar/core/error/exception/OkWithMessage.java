package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.LogModel;

public class OkWithMessage extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.OK_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.OK_MESSAGE;

  public OkWithMessage() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public OkWithMessage(final String errCode, final String errorMessage, final LogModel logModel) {
    super(errCode, errorMessage, logModel);
  }

  public OkWithMessage(final String errorMessage, final LogModel logModel) {
    super(ERROR_CODE, errorMessage, logModel);
  }

  public OkWithMessage(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

  public OkWithMessage(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }
}