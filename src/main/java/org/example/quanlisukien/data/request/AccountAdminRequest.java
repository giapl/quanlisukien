package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountAdminRequest {

  private String username;
  private String email;
  private String password;
  private String RoleName;
}
