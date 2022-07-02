package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstant {

  // GENERAL
  public static final ApiError CACHE_INITIALIZE_PROBLEM = ApiError.of(5000, "Problem initializing cache");
  public static final ApiError CACHE_PUT_PROBLEM = ApiError.of(5001, "Problem putting object in the cache");

  // URL
  public static final ApiError URL_NO_CONTENT_BY_ID = ApiError.of(6000, "Url can not find whit this id.");
  public static final ApiError URL_EXPIRATION_TIME = ApiError.of(6001, "Request for update url and expiration time parameter bigger than remaining time. Should be equal or smaller than remaining time. Pleas enter smaller expiration than remaining time. Also you should check account plan.");

  // ACCOUNT
  public static final ApiError ACCOUNT_NO_CONTENT_BY_ID = ApiError.of(6002, "Account can not find whit this id.");

  // VISITOR
  public static final ApiError VISITOR_NO_CONTENT_BY_ID = ApiError.of(6003, "Visitor can not find whit this id.");

  // QR FILE
  public static final ApiError QR_FILE_NO_CONTENT_BY_ID = ApiError.of(6004, "Qr file can not find whit this id.");




}
