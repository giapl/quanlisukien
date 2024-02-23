package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.response.EventsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEventsMapper {

  @Mapping(source = "categories.name", target = "name_category")
  @Mapping(source = "locations.name", target = "name_location")
  @Mapping(source = "locations.address", target = "address")
  @Mapping(source = "locations.description", target = "description_address")
  EventsResponse convertEntityEventsMapper(Events events); // convert events sang eventsResponse
}