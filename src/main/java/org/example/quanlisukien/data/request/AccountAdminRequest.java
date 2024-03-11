package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountAdminRequest {

  @NotBlank(message = "vui lòng nhập username")
  private String username;

  @NotBlank(message = "vui lòng nhập email")
  @Email(message = "nhập đúng định dạng email")
  private String email;

  @NotBlank(message = "vui lòng nhập password")
  @Size(min = 6, max = 18, message = "password tối thiểu 6 kí tự và tối đa 18 kí tự")
  private String password;

  @NotBlank(message = "vui lòng nhập quyền")
  private String RoleName;
}
