package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

  @NotBlank(message = "User ID cannot be null")
  private Long userId;

  @NotBlank(message = "Event ID cannot be null")
  private Long eventId;

  @NotBlank(message = "Feedback content cannot be null")
  private String feedbackContent;

  private String feedbackImage;
}
