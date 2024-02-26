package org.example.quanlisukien.service.events;

import java.util.List;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventsResponse;

public interface EventsService {

  Events createEvent(EventRequest eventRequest); //method tao 1 event moi do admin

  List<EventsResponse> findAll(); //method hien thi danh sach events

  void deleteByIdEvent(Long event_id); //method xoa event bang id

  Events updateByIdEvents(Long event_id , EventRequest eventRequest); // method update event bang id
}
