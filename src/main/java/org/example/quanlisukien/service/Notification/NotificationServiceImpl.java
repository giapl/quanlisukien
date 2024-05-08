package org.example.quanlisukien.service.Notification;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.example.quanlisukien.data.entity.Notifications;
import org.example.quanlisukien.data.entity.TokenFcm;
import org.example.quanlisukien.data.request.NotificationRequest;
import org.example.quanlisukien.data.request.TokenAppRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.mapper.INotificationsMapper;
import org.example.quanlisukien.repository.NotificationRepository;
import org.example.quanlisukien.repository.TokenAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {


  private final INotificationsMapper notificationsMapper;

  private final NotificationRepository notificationRepository;

  private final TokenAppRepository tokenAppRepository;

  @Autowired
  public NotificationServiceImpl(INotificationsMapper notificationsMapper,
      NotificationRepository notificationRepository, TokenAppRepository tokenAppRepository) {
    this.notificationsMapper = notificationsMapper;
    this.notificationRepository = notificationRepository;
    this.tokenAppRepository = tokenAppRepository;
  }

  @Override
  public String sendNotification(NotificationRequest notificationRequest)
      throws FirebaseMessagingException {

    // Tạo thông báo
    Notification notification = Notification.builder()
        .setTitle(notificationRequest.getTitle())
        .setBody(notificationRequest.getContent())
        .setImage(notificationRequest.getImageUrl())
        .build();

    // Lấy tất cả token từ cơ sở dữ liệu
    List<String> tokens = tokenAppRepository.findByAllToken();

    // Sử dụng Stream API để gửi thông báo đến mỗi token
    tokens.stream().forEach(token -> {
      Message message = Message.builder()
          .setNotification(notification)
          .setTopic(notificationRequest.getTopic())
          .setToken(token)
          .build();

      try {
        String response = FirebaseMessaging.getInstance().send(message);
        log.info("Successfully sent message: {}", response);
      } catch (FirebaseMessagingException e) {
        e.printStackTrace();
      }
    });

    saveNotificationFireStore(notificationRequest);
    saveNotificationEntity(notificationRequest);

    return "Notifications sent successfully";
  }

  @Override
  @Async
  public void saveNotificationFireStore(NotificationRequest notificationRequest) {

    Firestore db = FirestoreClient.getFirestore();

    // Tạo một map chứa dữ liệu của thông báo
    Map<String, Object> notificationData = new HashMap<>();
    notificationData.put("title", notificationRequest.getTitle());
    notificationData.put("content", notificationRequest.getContent());
    notificationData.put("imageUrl", notificationRequest.getImageUrl());


    // Thêm dữ liệu vào firestore
    ApiFuture<DocumentReference> addedDocRef = db.collection(notificationRequest.getTopic())
        .add(notificationData);

    try {
      log.info("Added document with ID: {}", addedDocRef.get().getId());
    } catch (InterruptedException | ExecutionException e) {
      log.error("Error adding document to Firestore: {}", e.getMessage());
    }
  }

  @Override
  @Async
  public void saveNotificationEntity(NotificationRequest notificationRequest) {

    try {
      Notifications notifications = notificationsMapper.convertToEntityNotification(
          notificationRequest);
      notificationRepository.save(notifications);
    } catch (Exception e) {
      throw new InternalServerException("Error when save notification");
    }
  }

  @Override
  public void saveToken(TokenAppRequest tokenAppRequest) {
    try {
      TokenFcm tokenFcm = new TokenFcm();
      tokenFcm.setToken(tokenAppRequest.getToken());
      tokenAppRepository.save(tokenFcm);
    } catch (Exception e) {
      throw new InternalServerException("Error when save token");
    }
  }
}

