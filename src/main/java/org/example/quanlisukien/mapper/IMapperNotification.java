package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Notifications;
import org.example.quanlisukien.data.request.EmailNotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapperNotification {

  @Mapping(source = "titleMail", target = "titleMail")
  @Mapping(source = "messengerMail", target = "messengerMail")
  @Mapping(target = "timeSend", expression = "java(java.time.LocalDateTime.now())")
  Notifications convertToEntity(EmailNotificationRequest emailNotificationRequest);

}
