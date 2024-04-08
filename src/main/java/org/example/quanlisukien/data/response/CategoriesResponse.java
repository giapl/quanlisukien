package org.example.quanlisukien.data.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriesResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long categoryId;
  private String name;
  private String description;
}
