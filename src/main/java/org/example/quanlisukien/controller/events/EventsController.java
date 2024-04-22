package org.example.quanlisukien.controller.events;

import jakarta.validation.Valid;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.request.EventSearchRequest;
import org.example.quanlisukien.data.response.EventRegistrationResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.example.quanlisukien.service.events.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {

  private final EventsService eventsService;

  @Autowired
  public EventsController(EventsService eventsService) {
    this.eventsService = eventsService;
  }

  @PostMapping("/create")
  public ResponseEntity<Events> createEvent(@Valid @RequestBody EventRequest eventRequest) {
    return ResponseEntity.ok(eventsService.createEvent(eventRequest));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteByIdEvent(@RequestParam Long eventId) {
    eventsService.deleteByIdEvent(eventId);
    return ResponseEntity.ok("delete event id successful");
  }

  @PutMapping("/update")
  public ResponseEntity<Events> updateByIdEvents(@RequestParam Long eventId, @RequestBody
  EventRequest eventRequest) {
    return ResponseEntity.ok(eventsService.updateByIdEvents(eventId, eventRequest));
  }

  @GetMapping("/search/eventRegistrations")
  public ResponseEntity<Page<EventRegistrationResponse>> getAllEventRegistration(
      @PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults(@SortDefault(sort = "dateTime",
          direction = Direction.DESC))
      Pageable pageable) {
    return ResponseEntity.ok(eventsService.getAllEventRegistration(pageable));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<EventsResponse>> searchAndFilter(
      @PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults(@SortDefault(sort = "dateTime",
          direction = Direction.DESC)) Pageable pageable,
      @RequestBody EventSearchRequest eventSearchRequest) {
    return ResponseEntity.ok(eventsService.search(pageable, eventSearchRequest));
  }
}
