package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSearchRequest {

  private String name_event;

  private String name_category;

  private String name_location;
}
