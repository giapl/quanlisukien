package org.example.quanlisukien.controller.notifications;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.quanlisukien.data.request.NotificationRequest;
import org.example.quanlisukien.data.request.TokenAppRequest;
import org.example.quanlisukien.service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

  private final NotificationService notificationService;

  @Autowired
  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping("/send")
  public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest)
      throws FirebaseMessagingException {
   notificationService.sendNotification(notificationRequest);
    return ResponseEntity.ok("send notification successful");
  }

  @PostMapping("/saveToken")
  public ResponseEntity<String> saveToken(@RequestBody TokenAppRequest token) {
    notificationService.saveToken(token);
    return ResponseEntity.ok("save token successful");
  }
}
