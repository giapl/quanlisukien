package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequest {

  @NotBlank(message = "Topic cannot be null")
  private String topic;

  @NotBlank(message = "Title cannot be null")
  private String title;

  @NotBlank(message = "Content cannot be null")
  private String content;

  private String imageUrl;

}
