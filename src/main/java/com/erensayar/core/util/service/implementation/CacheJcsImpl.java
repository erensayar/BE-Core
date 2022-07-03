package com.erensayar.core.util.service.implementation;

import com.erensayar.core.error.exception.ExceptionConstant;
import com.erensayar.core.error.exception.InternalServerErrorException;
import com.erensayar.core.log.LogModel;
import com.erensayar.core.util.service.CacheService;
import javax.annotation.PostConstruct;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CacheJcsImpl<T> implements CacheService<T> {

  private CacheAccess<String, T> cache = null;

  @PostConstruct
  public void init() {
    try {
      cache = JCS.getInstance("default");
    } catch (CacheException e) {
      throw new InternalServerErrorException(LogModel.builder()
          .apiError(ExceptionConstant.CACHE_INITIALIZE_PROBLEM)
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

  @Override
  public void put(String key, T t) {
    try {
      cache.put(key, t);
    } catch (CacheException e) {
      throw new InternalServerErrorException(LogModel.builder()
          .apiError(ExceptionConstant.CACHE_PUT_PROBLEM)
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

  @Override
  public T findByKey(String key) {
    return cache.get(key);
  }
}
