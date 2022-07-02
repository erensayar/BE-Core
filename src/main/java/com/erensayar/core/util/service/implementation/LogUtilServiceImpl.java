package com.erensayar.core.util.service.implementation;

import com.erensayar.core.error.exception.ExceptionConstant;
import com.erensayar.core.log.LogModel;
import com.erensayar.core.util.service.LogUtilService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LogUtilServiceImpl implements LogUtilService {

  // TODO: Remove

  @Override
  public LogModel createLogModelForUrl(String propertyName, String propertyVal) {
    return LogModel.builder()
        .data(Map.of("Url " + propertyName, propertyVal))
        .apiError(ExceptionConstant.URL_NO_CONTENT_BY_ID)
        .build();
  }

  @Override
  public LogModel createLogModelForAccount(String propertyName, String propertyVal) {
    return LogModel.builder()
        .data(Map.of("Account " + propertyName, propertyVal))
        .apiError(ExceptionConstant.ACCOUNT_NO_CONTENT_BY_ID)
        .build();
  }

}
