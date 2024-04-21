package org.example.quanlisukien.data.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EmailNotificationRequest {

  private String titleMail;
  private String messengerMail;
  private String sourceMail;
  private LocalDateTime timeSend;
}
