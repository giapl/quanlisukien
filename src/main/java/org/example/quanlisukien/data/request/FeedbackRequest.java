package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

  private Long user_id;

  private Long event_id;

  private String feedback_content;

  private String feedback_image;
}
