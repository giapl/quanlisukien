package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountLoginRequest {

  @NotBlank(message = "vui lòng nhập username")
  private String username;

  @NotBlank(message = "vui long nhập password")
  private String password;
}
