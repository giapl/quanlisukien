package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationsResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long registrationsId;

  private String username;

  private String fullName;

  private String email;

  private String phoneNumber;

  private String joinEvent;

  private String feelEvent;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime dateTime;
}
