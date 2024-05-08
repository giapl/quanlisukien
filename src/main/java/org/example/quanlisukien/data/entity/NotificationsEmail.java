package org.example.quanlisukien.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications_email")
public class NotificationsEmail {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "notification_email_id")
  private Long notificationId;

  @Column(name = "source_mail")
  private String sourceMail;

  @Column(name = "title_mail")
  private String titleMail;

  @Column(name = "messenger_mail")
  private String messengerMail;

  @Column(name = "time_send")
  private LocalDateTime timeSend;

}
