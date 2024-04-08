package org.example.quanlisukien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class Feedbacks implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "feedback_id")
  private Long feedbackId;
  @Column(name = "feedback_content")
  private String feedbackContent;
  @Column(name = "feedback_image")
  private String feedbackImage;

  @Column(name = "username")
  private String username;

  @Column(name = "create_at")
  private LocalDateTime dateTime;
  @Column(name = "update_at")
  private LocalDateTime updateTime;

  //lien ket den events
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "event_id")
  private Events events;

  //lien ket den account
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private Account account;
}
