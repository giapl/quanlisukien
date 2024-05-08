package org.example.quanlisukien.service.Notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.example.quanlisukien.data.request.NotificationRequest;
import org.example.quanlisukien.data.request.TokenAppRequest;

public interface NotificationService {

  String sendNotification(NotificationRequest notificationRequest) // send notification
      throws FirebaseMessagingException;

  void saveNotificationFireStore(NotificationRequest notificationRequest); // save notification

  void saveNotificationEntity(NotificationRequest notificationRequest); // save notification

  void saveToken(TokenAppRequest tokenAppRequest); // save token
}
