package com.erensayar.core.error.controller;

import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.error.exception.BaseException;
import com.erensayar.core.error.exception.BaseExceptionConstants;
import com.erensayar.core.error.exception.ConflictException;
import com.erensayar.core.error.exception.ForbiddenException;
import com.erensayar.core.error.exception.InternalServerErrorException;
import com.erensayar.core.error.exception.NoContentException;
import com.erensayar.core.error.exception.NotFoundException;
import com.erensayar.core.error.exception.OkWithMessage;
import com.erensayar.core.error.exception.TooManyRequestException;
import com.erensayar.core.error.exception.UnauthorizedException;
import com.erensayar.core.error.model.ErrorResponse;
import com.erensayar.core.error.model.ValidationErrorResponse;
import com.erensayar.core.log.LogModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_TYPE_VALUE = "application/json";

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequest(BadRequestException e) {
    return handle(e, HttpStatus.BAD_REQUEST, e.getLogModel());
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Object> handleConflictException(ConflictException e) {
    return handle(e, HttpStatus.CONFLICT, e.getLogModel());
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException e) {
    return handle(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getLogModel());
  }

  @ExceptionHandler(NoContentException.class)
  public ResponseEntity<Object> handleNoContentException(NoContentException e) {
    return handle(e, HttpStatus.NO_CONTENT, e.getLogModel());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
    return handle(e, HttpStatus.NOT_FOUND, e.getLogModel());
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
    return handle(e, HttpStatus.UNAUTHORIZED, e.getLogModel());
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Object> handleForbiddenException(ForbiddenException e) {
    return handle(e, HttpStatus.FORBIDDEN, e.getLogModel());
  }

  @ExceptionHandler(OkWithMessage.class)
  public ResponseEntity<Object> OkWithMessage(OkWithMessage e) {
    return handle(e, HttpStatus.OK, e.getLogModel());
  }

  @ExceptionHandler(TooManyRequestException.class)
  public ResponseEntity<Object> TooManyRequestException(TooManyRequestException e) {
    return handle(e, HttpStatus.TOO_MANY_REQUESTS, e.getLogModel());
  }

  private ResponseEntity<Object> handle(BaseException e, HttpStatus h, LogModel logModel) {
    logModel.createErrorLog();

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);

    ErrorResponse responseBody = ErrorResponse.builder()
        .errorCode(logModel.getApiError().getErrorCode().toString())
        .errorMessage(logModel.getApiError().getDescription())
        .build();

    return ResponseEntity
        .status(h)
        .headers(responseHeaders)
        .body(responseBody);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleJavaxValidationException(ConstraintViolationException e) {
    log.error(e.getMessage());

    Map<String, String> validationMessageAndProperty = new HashMap<>();
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    for (ConstraintViolation<?> c : constraintViolations) {
      validationMessageAndProperty.put(c.getPropertyPath().toString(), c.getMessage());
    }

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, CONTENT_TYPE_VALUE);

    ValidationErrorResponse responseBody = ValidationErrorResponse.builder()
        .errorType(e.getClass().getName())
        .errorCode(BaseExceptionConstants.VALIDATION_ERROR_CODE)
        .errorMessage(validationMessageAndProperty)
        .build();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .headers(responseHeaders)
        .body(responseBody);
  }

}
