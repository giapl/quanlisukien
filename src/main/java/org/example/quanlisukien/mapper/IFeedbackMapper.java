package org.example.quanlisukien.mapper;

import java.util.List;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.request.FeedbackRequest;
import org.example.quanlisukien.data.response.FeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IFeedbackMapper {

  FeedbackResponse map(Feedbacks feedbacks); //map 1 feedbacks sang feedbackResponse

  List<FeedbackResponse> convertFeedbackMapper(List<Feedbacks> feedbacks); //map 1 danh sach sang

  @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
  @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
  Feedbacks toFeedbacks(FeedbackRequest feedbackRequest);
}
