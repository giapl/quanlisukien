package org.example.quanlisukien.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "categories")
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long categoryId;
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

  @JsonManagedReference
  @OneToMany(mappedBy = "categories" , cascade = CascadeType.ALL , orphanRemoval = true)
  private List<Events> events;
}
