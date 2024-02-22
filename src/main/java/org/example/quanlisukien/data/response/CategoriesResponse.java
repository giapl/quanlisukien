package org.example.quanlisukien.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriesResponse {

  private Long category_id;
  private String name;
  private String description;
}
