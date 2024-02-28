package org.example.quanlisukien.mapper;

import java.util.List;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.response.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IFeedbackMapper {

  @Mapping(source = "account.username" , target = "username")
  List<FeedbackResponse> convertFeedbackMapper(List<Feedbacks> feedbacks);

}
