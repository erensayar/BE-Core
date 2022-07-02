package com.erensayar.core.util.service.implementation;

import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.log.LogModel;
import com.erensayar.core.util.service.FileUtilService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileUtilServiceImpl implements FileUtilService {

  @Override
  public void deleteFileFromHardDrive(String filePath) {
    Path path = Paths.get(filePath);
    try {
      Files.deleteIfExists(path);
    } catch (IOException e) {
      throw new BadRequestException(LogModel.builder()
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

  @Override
  public Boolean dirExistControl(String path) {
    Path target = Paths.get(path);
    return Files.isDirectory(target);
  }

  @Override
  public void createDirectory(String path) {
    Path target = Paths.get(path);
    try {
      Files.createDirectories(target);
    } catch (IOException e) {
      throw new BadRequestException(LogModel.builder()
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

}
