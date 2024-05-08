package org.example.quanlisukien.service.emaliService;

import java.util.concurrent.CompletableFuture;
import org.example.quanlisukien.data.request.EmailNotificationRequest;

public interface EmailNotificationService {

  String sendSimpleMail(EmailNotificationRequest emailRequest); //method sendEmailAll

  CompletableFuture<String> sendMail(String addressEmail,
      EmailNotificationRequest emailRequest); //method senEmail

  void saveNotification(EmailNotificationRequest emailRequest); //method saveNotification
}
