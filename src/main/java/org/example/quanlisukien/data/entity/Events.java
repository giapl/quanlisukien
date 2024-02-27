package org.example.quanlisukien.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Events {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id")
  private Long event_id;
  @Column(name = "name_event")
  private String name_event;
  @Column(name = "description")
  private String description;
  @Column(name = "event_image")
  private String event_image;

  @Column(name = "start_time")
  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime start_time;

  @Column(name = "end_time")
  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime end_time;

  @Column(name = "create_at")
  private LocalDateTime dateTime;
  @Column(name = "update_at")
  private LocalDateTime updateTime;

  //lien ket den feedback
  @JsonManagedReference
  @OneToMany(mappedBy = "events", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Feedbacks> feedbacks;

  //lien ket den dia chi to chuc su kien
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "location_id")
  private Locations locations;

  //lien ket den danh muc su kien
  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Categories categories;
}
