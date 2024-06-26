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
@Table(name = "notifications")
public class Notifications {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_id")
  private Long notificationId;

  @Column(name = "topic")
  private String topic;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "time_send")
  private LocalDateTime timeSend;
}
