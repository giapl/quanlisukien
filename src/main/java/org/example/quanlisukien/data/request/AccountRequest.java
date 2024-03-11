package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {

  @NotBlank(message = "không được để trống username")
  @Size(min = 6, max = 18, message = "username phải nhiều hơn 6 kí tự và tối đa 18 kí tự")
  private String username;

  @Email(message = "không được để trống email")
  @NotBlank(message = "email phải nhập đúng định dạng @gmail.com")
  @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "email không đúng định dạng")
  private String email;

  @NotBlank(message = "không được để trống password")
  @Size(min = 6, max = 18, message = "password phải tối thiểu 6 kí tự và tối đâ 18 kí tự")
  private String password;
}
