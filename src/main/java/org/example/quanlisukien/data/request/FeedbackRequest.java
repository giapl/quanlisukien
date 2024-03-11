package org.example.quanlisukien.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

  private Long user_id;

  private Long event_id;

  @NotEmpty(message = "không được để trống bình luận")
  private String feedback_content;

  private String feedback_image;
}
