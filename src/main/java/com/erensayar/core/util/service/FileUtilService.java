package com.erensayar.core.util.service;

public interface FileUtilService {

  void deleteFileFromHardDrive(String filePath);

  Boolean dirExistControl(String path);

  void createDirectory(String path);

}
