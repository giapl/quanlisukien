package org.example.quanlisukien.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

  @NotBlank(message = "không được để trống tên sự kiện")
  private String name_event;

  @NotBlank(message = "không được để trống mô tả sự kiện")
  private String description_event;

  private String event_image;

  @NotBlank(message = "không được để trống tên danh mục sự kiện")
  private String name_category;

  @NotBlank(message = "không được để trống tên địa chỉ")
  private String name_location;

  @NotBlank(message = "không được để trống địa chỉ cụ thể")
  private String address;

  @NotBlank(message = "không được để trống mô tả địa điểm")
  private String description_address;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime start_time;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime end_time;

}
