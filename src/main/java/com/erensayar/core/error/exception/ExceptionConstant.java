package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstant {

  // GENERAL
  public static final ApiError CACHE_INITIALIZE_PROBLEM =
      ApiError.of(5000, "Problem initializing cache");

  public static final ApiError CACHE_PUT_PROBLEM =
      ApiError.of(5001, "Problem putting object in the cache");

}
