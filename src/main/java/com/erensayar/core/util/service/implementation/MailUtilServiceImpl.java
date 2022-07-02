package com.erensayar.core.util.service.implementation;

import com.erensayar.core.util.model.dto.Mail;
import com.erensayar.core.util.model.dto.MailChangeDto;
import com.erensayar.core.util.model.enums.MailTemplateName;
import com.erensayar.core.util.service.CacheService;
import com.erensayar.core.util.service.MailUtilService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailUtilServiceImpl implements MailUtilService {

  // TODO: !!! There are so many repetitions !!! WILL BE REFACTOR !!!

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;
  private final CacheService<MailChangeDto> cacheService;

  @Override
  public void sendMail(Mail mail) {
    SimpleMailMessage m = new SimpleMailMessage();
    m.setTo(mail.getTo());
    if (mail.getCc() != null) {
      m.setCc(mail.getCc());
    }
    if (mail.getBcc() != null) {
      m.setBcc(mail.getBcc());
    }
    m.setSubject(mail.getSubject());
    m.setText(mail.getBody());
    javaMailSender.send(m);
  }

  @Override
  public void sendMailWithAttachment(Mail mail, MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      //MimeMessageHelper helper = new MimeMessageHelper(m, true); // true -> multipart message
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
      if (mail.getCc() != null) {
        helper.setCc(mail.getCc());
      }
      if (mail.getBcc() != null) {
        helper.setBcc(mail.getBcc());
      }
      helper.setSubject(mail.getSubject());
      helper.setText(mail.getBody());

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream,
          "application/octet-stream");
      helper.addAttachment(attachment.getOriginalFilename(), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void sendMailAsTemplate(Mail mail, Integer mailTemplateNo) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      //MimeMessageHelper helper = new MimeMessageHelper(m, true); // true -> multipart message
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
      if (mail.getCc() != null) {
        helper.setCc(mail.getCc());
      }
      if (mail.getBcc() != null) {
        helper.setBcc(mail.getBcc());
      }
      helper.setSubject(mail.getSubject());

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      helper.setText(html, true); // Set mail body as template

      javaMailSender.send(msg);
    } catch (MessagingException e) {
      log.error(e.getMessage());
    }
  }

  @Override
  public void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo,
      MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(
          msg,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      // Create Mail
      helper.setTo(mail.getTo());
      if (mail.getCc() != null) {
        helper.setCc(mail.getCc());
      }
      if (mail.getBcc() != null) {
        helper.setBcc(mail.getBcc());
      }
      helper.setSubject(mail.getSubject());

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      helper.setText(html, true); // Set mail body as template

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream, "application/octet-stream");
      helper.addAttachment(attachment.getOriginalFilename(), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * This method does receive new mail from client and insert to cache also generate confirm code.
   * @param accountId Account id
   * @param newMail New Mail
   */
  @Override
  public void mailChangeRequest(UUID accountId, String newMail) {
    String key = UUID.randomUUID().toString().replace("-", "");
    cacheService.put(key, MailChangeDto.builder()
        .accountId(accountId)
        .newMail(newMail)
        .build());
  }

  /**
   * Client click the generated confirm link then run here and get the object by key from cache.
   * If confirm code is present in the cache mail change process can be start.
   * @param confirmCode client send the received confirm code. This code generate in mail change
   *                    request method if they equal it's done.
   * @return New Mail
   */
  @Override
  public MailChangeDto confirmMailChangeRequest(String confirmCode) {
    return cacheService.findByKey(confirmCode);
  }


}
