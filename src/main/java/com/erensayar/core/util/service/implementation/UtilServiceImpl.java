package com.erensayar.core.util.service.implementation;

import com.erensayar.core.util.service.UtilService;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UtilServiceImpl implements UtilService {

  static final String alphaNumericString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  static SecureRandom rnd = new SecureRandom();
  private final Environment environment;

  @Override
  public String generateRandomString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(alphaNumericString.charAt(rnd.nextInt(alphaNumericString.length())));
    }
    return sb.toString();
  }

  @Override
  public String[] getActiveProfiles() {
    return environment.getActiveProfiles();
  }

  @Override
  public Boolean activeProfileCheck(String profile) {
    for (String ap : getActiveProfiles()) {
      if (ap.equals(profile)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Integer generateRandomNumberAdjustedRange(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }

  @Override
  public Field[] getClassFields(Object object) {
    return object.getClass().getDeclaredFields();
  }

  @Override
  public void printClassFields(Object object) {
    Field[] fields = getClassFields(object);
    for (Field field : fields) {
      System.out.println(field.getName() + ": " + field.getType().getSimpleName());
    }
  }

  @Override
  public <T> boolean isRetainAllInClassTheseFields(Class<T> c, Set<String> inputFieldNames) {
    List<String> classFieldNames = new java.util.ArrayList<>(
        Arrays.stream(c.getDeclaredFields()).map(Field::getName).toList());
    return classFieldNames.retainAll(inputFieldNames);
  }

}
