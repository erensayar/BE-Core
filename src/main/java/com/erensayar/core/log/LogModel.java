package com.erensayar.core.log;

import com.erensayar.core.error.model.ApiError;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class LogModel {

  // Default Create
  private UUID traceId;
  private String classNameOfLogPrinted;
  private String methodNameOfLogPrinted;

  // Custom Create
  private String userId;                // if we have - maybe take from principal
  private ApiError apiError;            // problem code, problem description (business)
  private Map<String, String> data;     // entity name , entity id etc...
  private String javaExceptionName;     // source exception name
  private String javaExceptionMessage;  // source exception message

  public LogModel(
      UUID traceId,
      String classNameOfLogPrinted,
      String methodNameOfLogPrinted,
      String userId,
      ApiError apiError,
      Map<String, String> data,
      String javaExceptionName,
      String javaExceptionMessage) {

    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    this.traceId = UUID.randomUUID();
    this.classNameOfLogPrinted = ste[5].getClassName();
    this.methodNameOfLogPrinted = ste[5].getMethodName();
    this.userId = userId;
    this.apiError = apiError;
    this.data = data;
    this.javaExceptionName = javaExceptionName;
    this.javaExceptionMessage = javaExceptionMessage;
  }

  // Builder customization
  // <=============================================================================================>
  public static LogModelBuilder builder() {
    return new CustomLogModelBuilder();
  }

  public void createErrorLog() {
    log.error(toString());
  }

  public void createInfoLog() {
    log.info(toString());
  }

  public void createDebugLog() {
    log.debug(toString());
  }

  public void createWarnLog() {
    log.warn(toString());
  }

  private static class CustomLogModelBuilder extends LogModelBuilder {

    @Override
    public LogModel build() {
      StackTraceElement[] ste = Thread.currentThread().getStackTrace();
      super.traceId(UUID.randomUUID());
      super.methodNameOfLogPrinted(ste[1].getMethodName()); // TODO : index 1 dogru mu? kontrol et
      super.classNameOfLogPrinted(ste[1].getClassName());
      return super.build();
    }

  }
  // <=============================================================================================>

}
