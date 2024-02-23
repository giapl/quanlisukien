package org.example.quanlisukien.controller.events;

import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.service.events.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest) {
    return ResponseEntity.ok(eventsService.createEvent(eventRequest));
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(eventsService.findAll());
  }

}
