package com.erensayar.core.util.service;

import com.erensayar.core.util.model.dto.Mail;
import com.erensayar.core.util.model.dto.MailChangeDto;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public interface MailUtilService {

  void sendMail(Mail mail);

  void sendMailWithAttachment(Mail mail, MultipartFile attachment);

  void sendMailAsTemplate(Mail mail, Integer mailTemplateNo);

  void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo, MultipartFile attachment);

  void mailChangeRequest(UUID accountId, String newMail);

  MailChangeDto confirmMailChangeRequest(String confirmCode);

}
