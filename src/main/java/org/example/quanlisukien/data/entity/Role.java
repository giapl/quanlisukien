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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "roleId")
  private Long role_id;
  @Column(name = "roleName")
  private String RoleName;

//  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  @Column(name = "createAt")
  private LocalDateTime dateTime;

//  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  @Column(name = "UpdateAt")
  private LocalDateTime updateTime;

  @JsonManagedReference
  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , orphanRemoval = true)
  private List<Account> accounts;
}
