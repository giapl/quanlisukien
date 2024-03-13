package org.example.quanlisukien.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSearchRequest {

  private String nameEvent;

  private String nameCategory;

  private String nameLocation;
}
