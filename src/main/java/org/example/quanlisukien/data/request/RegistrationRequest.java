package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

  private Long eventId;

  private Long userId;

  private String username;

  @NotBlank(message = "không được để trống họ và tên")
  private String fullName;

  @NotBlank(message = "không được để tróng emali")
  @Email(message = "không được để trống email")
  @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
      message = "email không đúng định dạng")
  private String email;

  @NotNull(message = "không được để trống số điện thoại")
  @Min(value = 10,message = "nhập đúng định dạng số điện thoại")
  private Long phoneNumber;

  private String joinEvent;

  private String feelEvent;

}
