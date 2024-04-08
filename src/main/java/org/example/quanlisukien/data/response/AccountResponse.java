package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AccountResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userId;
  private String username;
  private String email;
  private String password;
  private String roleName;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime dateTime;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime updateTime;
}
