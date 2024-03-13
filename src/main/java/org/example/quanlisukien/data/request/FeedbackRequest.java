package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

  private Long userId;

  private Long eventId;

  private String feedbackContent;

  private String feedbackImage;
}
