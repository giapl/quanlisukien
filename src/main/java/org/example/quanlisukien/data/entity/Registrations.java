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
@Table(name = "registrations")
public class Registrations implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "registrations_id")
  private Long registrationsId;

  @Column(name = "username")
  private String username;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private Long phoneNumber;

  @Column(name = "join_event")
  private String joinEvent;

  @Column(name = "feel_event")
  private String feelEvent;

  @Column(name = "create_at")
  private LocalDateTime dateTime;

  //lien ket voi events
  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id")
  private Events events;

  //lien ket voi user

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Account account;
}
