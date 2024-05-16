package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.NotificationsEmail;
import org.example.quanlisukien.data.request.EmailNotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMapperNotificationEmail {


  @Mapping(target = "timeSend", expression = "java(java.time.LocalDateTime.now())")
  NotificationsEmail convertToEntity(EmailNotificationRequest emailNotificationRequest);

}
