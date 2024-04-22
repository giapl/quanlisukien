package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Registrations;
import org.example.quanlisukien.data.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRegistrationMapper {

  @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
  Registrations convertToEntity(RegistrationRequest registrationRequest);
}
