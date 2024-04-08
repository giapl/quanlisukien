package org.example.quanlisukien.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "không được để trống tên sự kiện")
  private String nameEvent;

  @NotBlank(message = "không được để trống mô tả sự kiện")
  private String descriptionEvent;

  private String eventImage;

  @NotBlank(message = "không được để trống tên danh mục sự kiện")
  private String nameCategory;

  @NotBlank(message = "không được để trống tên địa chỉ")
  private String nameLocation;

  @NotBlank(message = "không được để trống địa chỉ cụ thể")
  private String address;

  @NotBlank(message = "không được để trống mô tả địa điểm")
  private String descriptionAddress;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;

}
