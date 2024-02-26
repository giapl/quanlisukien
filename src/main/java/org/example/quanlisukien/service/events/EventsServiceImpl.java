package org.example.quanlisukien.service.events;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Locations;
import org.example.quanlisukien.data.request.EventAdminRequest;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventsResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IEventsMapper;
import org.example.quanlisukien.repository.CategoriesRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.LocationsRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventsServiceImpl implements EventsService {

  private final CategoriesRepository categoriesRepository;

  private final EventsRepository eventsRepository;

  private final LocationsRepository locationsRepository;

  private final IEventsMapper iEventsMapper;

  public EventsServiceImpl(CategoriesRepository categoriesRepository,
      EventsRepository eventsRepository, LocationsRepository locationsRepository,
      IEventsMapper iEventsMapper) {
    this.categoriesRepository = categoriesRepository;
    this.eventsRepository = eventsRepository;
    this.locationsRepository = locationsRepository;
    this.iEventsMapper = iEventsMapper;
  }

  @Override
  public Events createEvent(EventRequest eventRequest) {

    Categories categories = categoriesRepository.findByName(eventRequest.getName_category())
        .get(); //tim kiem danh muc

    Locations locations = new Locations(); //tao ra 1 dia diem to chuc event
    locations.setName(eventRequest.getName_location());
    locations.setAddress(eventRequest.getAddress());
    locations.setDescription(eventRequest.getDescription_address());
    locations.setDateTime(LocalDateTime.now());
    locations.setUpdateTime(LocalDateTime.now());

    List<Locations> locationsList = new ArrayList<>();
    locationsList.add(locations); // add vao locations
    locationsRepository.save(locations);

    Events events = new Events();
    events.setName_event(eventRequest.getName_event());
    events.setDescription(eventRequest.getDescription_event());
    events.setEvent_image(eventRequest.getEvent_image());
    events.setStart_time(eventRequest.getStart_time());
    events.setEnd_time(eventRequest.getEnd_time());

    events.setCategories(categories); // add danh muc vao

    events.setLocations(locations);

    events.setDateTime(LocalDateTime.now());
    events.setUpdateTime(LocalDateTime.now());

    try {
      return eventsRepository.save(events);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database admin");
    }
  }

  @Override
  public List<EventsResponse> findAll() {
    return eventsRepository.findAll()
        .stream()
        .map(iEventsMapper::convertEntityEventsMapper)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteByIdEvent(Long event_id) {
    if (eventsRepository.existsById(event_id)) { //kiem tra xem id co ton tai ko
      eventsRepository.deleteById(event_id);
    } else {
      throw new NotFoundException("no id delete admin");
    }
  }

  @Override
  public Events updateByIdEvents(Long event_id, EventAdminRequest eventAdminRequest) {
    Optional<Events> optionalEvents = eventsRepository.findById(event_id);
    if (optionalEvents.isPresent()) {
      Events events = optionalEvents.get();
      if (eventAdminRequest.getName_event() != null) {
        events.setName_event(eventAdminRequest.getName_event());
      }
      if (eventAdminRequest.getDescription_event() != null) {
        events.setDescription(eventAdminRequest.getDescription_event());
      }
      if (eventAdminRequest.getEvent_image() != null) {
        events.setEvent_image(eventAdminRequest.getEvent_image());
      }

      if (eventAdminRequest.getName_category() != null) {
        Categories categories = categoriesRepository.findByName(
                eventAdminRequest.getName_category())
            .get();
        events.setCategories(categories);
      }

      if (eventAdminRequest.getStart_time() != null) {
        events.setStart_time(eventAdminRequest.getStart_time());
      }

      if (eventAdminRequest.getEnd_time() != null) {
        events.setEnd_time(eventAdminRequest.getEnd_time());
      }
      events.setUpdateTime(LocalDateTime.now());
      return eventsRepository.save(events);
    } else {
      throw new NotFoundException("no id event update admin");
    }
  }
}
