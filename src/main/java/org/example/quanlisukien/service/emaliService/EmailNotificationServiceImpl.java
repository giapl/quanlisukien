package org.example.quanlisukien.service.emaliService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.example.quanlisukien.data.entity.NotificationsEmail;
import org.example.quanlisukien.data.request.EmailNotificationRequest;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IMapperNotificationEmail;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.NotificationEmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

  @Value("${spring.mail.username}")
  private String Mail;

  private final JavaMailSender javaMailSender;
  private final AccountRepository accountRepository;
  private final NotificationEmailRepository notificationRepository;
  private final IMapperNotificationEmail iMapperNotificationEmail;

  public EmailNotificationServiceImpl(JavaMailSender javaMailSender,
      AccountRepository accountRepository,
      NotificationEmailRepository notificationRepository, IMapperNotificationEmail iMapperNotificationEmail) {
    this.javaMailSender = javaMailSender;
    this.accountRepository = accountRepository;
    this.notificationRepository = notificationRepository;
    this.iMapperNotificationEmail = iMapperNotificationEmail;
  }

  @Override
  public String sendSimpleMail(EmailNotificationRequest emailRequest) {
    List<String> userMail = accountRepository.findByEmail();
    userMail.forEach(email -> sendMail(email, emailRequest)); // send email to all email in list
    saveNotification(emailRequest); // save notification
    return "email sent successfully";
  }

  // sử dụng CompletableFuture để xử lý bất đồng bộ
  @Override
  @Async
  public CompletableFuture<String> sendMail(String addressEmail,
      EmailNotificationRequest emailRequest) {
    try {

      // create email
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(Mail);
      simpleMailMessage.setText(emailRequest.getMessengerMail());
      simpleMailMessage.setSubject(emailRequest.getTitleMail());
      simpleMailMessage.setTo(addressEmail);

      javaMailSender.send(simpleMailMessage); // send email

      return CompletableFuture.completedFuture("Mail sent successfully");

    } catch (Exception e) {
      throw new NotFoundException("Mail sent failed");
    }
  }

  // sử dụng @Async để xử lý bất đồng bộ
  @Override
  @Async
  public void saveNotification(EmailNotificationRequest emailRequest) {
    // save notification
    NotificationsEmail notifications = iMapperNotificationEmail.convertToEntity(emailRequest);
    notificationRepository.save(notifications);
  }
}
