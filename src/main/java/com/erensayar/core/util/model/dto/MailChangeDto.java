package com.erensayar.core.util.model.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailChangeDto {
  private UUID accountId;
  private String newMail;
}
