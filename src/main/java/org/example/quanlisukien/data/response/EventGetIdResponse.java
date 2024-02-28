package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class EventGetIdResponse {

  private Long event_id;
  private String name_event;
  private String description;
  private String event_image;
  private String name_category;
  private String name_location;
  private String address;
  private String description_address;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime start_time;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime end_time;

  private List<FeedbackResponse> feedback;
}
