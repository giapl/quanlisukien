package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventRegistrationResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IEventsMapper {


  @Mapping(source = "categories.name", target = "nameCategory")
  @Mapping(source = "locations.name", target = "nameLocation")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "descriptionAddress")
  @Mapping(source = "events.feedbacks", target = "feedback")
  EventsResponse convertEntityEventsMapper(
      Events events); // convert events sang eventsResponse cho method all event and name_event and name_category

  @Mapping(source = "events.eventId", target = "eventId")
  @Mapping(source = "events.description",target = "descriptionEvent")
  @Mapping(source = "categories.name", target = "nameCategory")
  @Mapping(source = "locations.name", target = "nameLocation")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "descriptionAddress")
  @Mapping(source = "events.feedbacks", target = "feedback")
  @Mapping(source = "events.registrations", target = "registrations")
  EventRegistrationResponse convertEventRegistrationMapper(
      Events events); //convert method getAllEventRegistration

  @Mapping(source = "descriptionEvent", target = "description")
  @Mapping(source = "eventImage", target = "eventImage")
  @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Mapping(target = "dateTime", expression = "java(java.time.LocalDateTime.now())")
  @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())")
  Events convertEventsEntityMapper(
      EventRequest eventRequest); //map tu eventRequest sang events Entity
}
