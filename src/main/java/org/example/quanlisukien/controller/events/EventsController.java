package org.example.quanlisukien.controller.events;

import jakarta.validation.Valid;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.request.EventSearchRequest;
import org.example.quanlisukien.service.events.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<?> createEvent(@Valid @RequestBody EventRequest eventRequest) {
    return ResponseEntity.ok(eventsService.createEvent(eventRequest));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteByIdEvent(@RequestParam Long event_id) {
    eventsService.deleteByIdEvent(event_id);
    return ResponseEntity.ok("delete event id successful");
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateByIdEvents(@RequestParam Long event_id, @RequestBody
  EventRequest eventRequest) {
    eventsService.updateByIdEvents(event_id, eventRequest);
    return ResponseEntity.ok("update event successful admin");
  }

  @GetMapping("/all/{ofSize}")
  public ResponseEntity<?> getAll(@PathVariable int ofSize, Pageable pageable) {
    return ResponseEntity.ok(eventsService.getAll(ofSize, pageable));
  }

  @GetMapping("/search/nameEvent/{ofSize}")
  public ResponseEntity<?> getByNameEvent(@PathVariable int ofSize,
      @RequestParam String name_event, Pageable pageable) {
    return ResponseEntity.ok(eventsService.getByNameEvent(ofSize, name_event, pageable));
  }

  @GetMapping("/search/{offSize}/{name_category}")
  public ResponseEntity<?> getByCategoryName(@PathVariable int offSize,
      @PathVariable String name_category, Pageable pageable) {
    return ResponseEntity.ok(eventsService.getByCategoryName(offSize, name_category, pageable));
  }

  @GetMapping("/search/{offSize}/eventRegistrations")
  public ResponseEntity<?> getAllEventRegistration(@PathVariable int offSize, Pageable pageable) {
    return ResponseEntity.ok(eventsService.getAllEventRegistration(offSize, pageable));
  }

  @PostMapping("/search")
  public ResponseEntity<?> getAll(@RequestParam int offSize, Pageable pageable,
      @RequestBody EventSearchRequest eventSearchRequest) {
    return ResponseEntity.ok(eventsService.search(offSize, pageable, eventSearchRequest));
  }
}
