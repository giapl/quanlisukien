package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Notifications;
import org.example.quanlisukien.data.request.NotificationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface INotificationsMapper {

  @Mapping(target = "timeSend", expression = "java(java.time.LocalDateTime.now())")
  Notifications convertToEntityNotification(NotificationRequest notificationRequest);
}
