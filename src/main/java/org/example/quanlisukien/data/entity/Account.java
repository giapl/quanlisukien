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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Account")
public class Account implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long user_id;
  @Column(name = "username")
  private String username;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  @Column(name = "create_at")
  private LocalDateTime dateTime;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  @Column(name = "update_at")
  private LocalDateTime updateTime;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_Id")
  private Role role;

  @JsonManagedReference
  @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Feedbacks> feedbacks;

  //lien ket voi dang ki su kien
  @JsonManagedReference
  @OneToMany(mappedBy = "account",orphanRemoval = true,cascade = CascadeType.ALL)
  private List<Registrations> registrations;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
