package org.example.quanlisukien.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long roleId;

  private String RoleName;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime dateTime;

  @JsonFormat(pattern = "yyyy:MM:DD hh:mm:ss")
  private LocalDateTime updateTime;
}
