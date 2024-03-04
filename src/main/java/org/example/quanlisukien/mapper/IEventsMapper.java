package org.example.quanlisukien.mapper;


import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.response.EventGetIdResponse;
import org.example.quanlisukien.data.response.EventRegistrationResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IEventsMapper {

  @Mapping(source = "categories.name", target = "name_category")
  @Mapping(source = "locations.name", target = "name_location")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "description_address")
  @Mapping(source = "events.feedbacks", target = "feedback")
  EventsResponse convertEntityEventsMapper(
      Events events); // convert events sang eventsResponse cho method all event and name_event and name_category

  @Mapping(source = "categories.name", target = "name_category")
  @Mapping(source = "locations.name", target = "name_location")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "description_address")
  EventGetIdResponse convertEventMapper(Events events); // convert method getById event

  @Mapping(source = "events.event_id", target = "event_id")
  @Mapping(source = "categories.name", target = "name_category")
  @Mapping(source = "locations.name", target = "name_location")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "description_address")
  @Mapping(source = "events.feedbacks", target = "feedback")
  @Mapping(source = "events.registrations", target = "registrations")
  EventRegistrationResponse convertEventRegistrationMapper(
      Events events); //convert method getAllEventRegistration
}
