package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationResponse {

  private Long event_id;

  private String name_event;

  private String description_event;

  private String event_image;

  private String name_category;

  private String name_location;

  private String address;

  private String description_address;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime start_time;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime end_time;

  private Long numberFeedback;

  private List<FeedbackResponse> feedback;

  private Long numberRegistration;

  private List<RegistrationsResponse> registrations;
}
