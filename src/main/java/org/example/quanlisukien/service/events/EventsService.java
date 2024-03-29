package org.example.quanlisukien.service.events;


import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.request.EventSearchRequest;
import org.example.quanlisukien.data.response.EventRegistrationResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface EventsService {

  Events createEvent(EventRequest eventRequest); //method tao 1 event moi do admin

  void deleteByIdEvent(Long eventId); //method xoa event bang id

  Events updateByIdEvents(Long eventId, EventRequest eventRequest); // method update event bang id

  Page<EventRegistrationResponse> getAllEventRegistration(
      Pageable pageable); //method admin getAll event and feedback and registrations

  Specification<Events> createEventSpec(EventSearchRequest search); // create search and filter

  Page<EventsResponse> search(Pageable pageable,
      EventSearchRequest eventSearchRequest); //method search and filter
}
