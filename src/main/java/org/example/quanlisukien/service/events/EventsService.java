package org.example.quanlisukien.service.events;

import java.util.List;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventGetIdResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventsService {

  Events createEvent(EventRequest eventRequest); //method tao 1 event moi do admin

  void deleteByIdEvent(Long event_id); //method xoa event bang id

  Events updateByIdEvents(Long event_id , EventRequest eventRequest); // method update event bang id

  EventGetIdResponse getById(Long id); // method tim kiem event bang id;

  Page<EventsResponse> getAll(Pageable pageable); //method hien thi event day du

}
