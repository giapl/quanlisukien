package org.example.quanlisukien.service.events;


import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventGetIdResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventsService {

  Events createEvent(EventRequest eventRequest); //method tao 1 event moi do admin

  void deleteByIdEvent(Long event_id); //method xoa event bang id

  Events updateByIdEvents(Long event_id, EventRequest eventRequest); // method update event bang id

  EventGetIdResponse getById(Long id); // method tim kiem event bang id;

  Page<EventsResponse> getAll(int ofSize, Pageable pageable); //method hien thi event phan trang

  Page<EventsResponse> getByName_event(int ofSize, String name_event,
      Pageable pageable); //tim kiem event theo ten event

  Page<EventsResponse> getByCategoryName(int offSize, String name_category,
      Pageable pageable); //method getBy name_category lay ra event
}
