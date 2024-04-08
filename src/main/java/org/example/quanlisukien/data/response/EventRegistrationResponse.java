package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long eventId;

  private String nameEvent;

  private String descriptionEvent;

  private String eventImage;

  private String nameCategory;

  private String nameLocation;

  private String address;

  private String descriptionAddress;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;

  private Long numberFeedback;

  private List<FeedbackResponse> feedback;

  private Long numberRegistration;

  private List<RegistrationsResponse> registrations;
}
