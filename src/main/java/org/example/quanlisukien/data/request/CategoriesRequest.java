package org.example.quanlisukien.data.request;

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
public class CategoriesRequest {

  @NotBlank(message = "không được để trống name_event")
  @Size(min = 1, max = 200,message = "nhập tên danh mục phải có độ dài lớn hơn 1")
  private String name;

  @NotBlank(message = "không được để trống mô tả")
  private String description;
}
