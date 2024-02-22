package org.example.quanlisukien.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "categories")
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long category_id;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;

  @Column(name = "create_at")
  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime dateTime;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  @Column(name = "update_at")
  private LocalDateTime updateTime;
}
