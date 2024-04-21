package org.example.quanlisukien.controller.email;

import org.example.quanlisukien.data.request.EmailNotificationRequest;
import org.example.quanlisukien.service.emaliService.EmailNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailNotificationController {

  private final EmailNotificationService emailService;

  public EmailNotificationController(EmailNotificationService emailService) {
    this.emailService = emailService;
  }
  @PostMapping("/send")
  public ResponseEntity<?> sendMail(@RequestBody EmailNotificationRequest emailRequest) {
    return ResponseEntity.ok(emailService.sendSimpleMail(emailRequest));
  }
}
