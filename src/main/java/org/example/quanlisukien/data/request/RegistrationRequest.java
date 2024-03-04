package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

  private Long event_id;

  private Long user_id;

  private String username;

  private String fullName;

  private String email;

  private Long phoneNumber;

  private String joinEvent;

  private String feelEvent;

}
