package com.erensayar.core.util.service;

import com.erensayar.core.log.LogModel;

// TODO: Remove
public interface LogUtilService {

  LogModel createLogModelForUrl(String propertyName, String propertyVal);

  LogModel createLogModelForAccount(String propertyName, String propertyVal);

}
