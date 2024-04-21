package org.example.quanlisukien.service.emaliService;

import java.util.List;
import java.util.stream.Collectors;
import org.example.quanlisukien.data.entity.Notifications;
import org.example.quanlisukien.data.request.EmailNotificationRequest;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IMapperNotification;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

  @Value("${spring.mail.username}")
  private String Mail;

  private final JavaMailSender javaMailSender;
  private final AccountRepository accountRepository;
  private final NotificationRepository notificationRepository;
  private final IMapperNotification iMapperNotification;

  public EmailNotificationServiceImpl(JavaMailSender javaMailSender, AccountRepository accountRepository,
      NotificationRepository notificationRepository, IMapperNotification iMapperNotification) {
    this.javaMailSender = javaMailSender;
    this.accountRepository = accountRepository;
    this.notificationRepository = notificationRepository;
    this.iMapperNotification = iMapperNotification;
  }

  @Override
  public String sendSimpleMail(EmailNotificationRequest emailRequest) {
    List<String> userMail = accountRepository.findAll()
        .stream()
        .map(account -> account.getEmail())// get email
        .collect(Collectors.toList()); // collect to list
    userMail.forEach(email -> sendMail(email, emailRequest)); // send email to all email in list
    saveNotification(emailRequest); // save notification
    return "email sent successfully";
  }

  @Override
  public String sendMail(String addressEmail, EmailNotificationRequest emailRequest) {
    try {

      // create email
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setFrom(Mail);
      simpleMailMessage.setText(emailRequest.getMessengerMail());
      simpleMailMessage.setSubject(emailRequest.getTitleMail());
      simpleMailMessage.setTo(addressEmail);

      javaMailSender.send(simpleMailMessage); // send email

      return "Mail sent successfully";

    } catch (Exception e) {
      throw new NotFoundException("Mail sent failed");
    }
  }

  @Override
  public void saveNotification(EmailNotificationRequest emailRequest) {
    // save notification
    Notifications notifications = iMapperNotification.convertToEntity(emailRequest);
    notificationRepository.save(notifications);
  }
}
