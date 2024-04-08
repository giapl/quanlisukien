package org.example.quanlisukien.service.events;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Locations;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.request.EventSearchRequest;
import org.example.quanlisukien.data.response.EventRegistrationResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IEventsMapper;
import org.example.quanlisukien.repository.CategoriesRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventsServiceImpl implements EventsService {

  private final CategoriesRepository categoriesRepository;

  private final EventsRepository eventsRepository;

  private final LocationsRepository locationsRepository;

  private final IEventsMapper iEventsMapper;


  @Autowired
  public EventsServiceImpl(CategoriesRepository categoriesRepository,
      EventsRepository eventsRepository, LocationsRepository locationsRepository,
      IEventsMapper iEventsMapper) {
    this.categoriesRepository = categoriesRepository;
    this.eventsRepository = eventsRepository;
    this.locationsRepository = locationsRepository;
    this.iEventsMapper = iEventsMapper;
  }

  @Override
  @CacheEvict(value = "eventRegistration", allEntries = true)
  public Events createEvent(EventRequest eventRequest) {

    Categories categories = categoriesRepository.findByName(eventRequest.getNameCategory())
        .get(); //tim kiem danh muc

    Locations locations = new Locations(); //tao ra 1 dia diem to chuc event
    locations.setName(eventRequest.getNameLocation());
    locations.setAddress(eventRequest.getAddress());
    locations.setDescription(eventRequest.getDescriptionAddress());
    locations.setDateTime(LocalDateTime.now());
    locations.setUpdateTime(LocalDateTime.now());

    List<Locations> locationsList = new ArrayList<>();
    locationsList.add(locations); // add vao locations
    locationsRepository.save(locations);

    Events events = iEventsMapper.convertEventsEntityMapper(
        eventRequest); //map tu eventRequest sang entity events
    events.setCategories(categories);
    events.setLocations(locations);

    try {
      return eventsRepository.save(events);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database admin");
    }
  }

  @Override
  @CacheEvict(value = "eventRegistration", allEntries = true)
  public void deleteByIdEvent(Long eventId) {
    if (eventsRepository.existsById(eventId)) { //kiem tra xem id co ton tai ko
      eventsRepository.deleteById(eventId);
    } else {
      throw new NotFoundException("no id delete admin");
    }
  }

  @Override
  @CachePut(value = "eventRegistration")
  public Events updateByIdEvents(Long eventId, EventRequest eventRequest) {
    Optional<Events> optionalEvents = eventsRepository.findById(eventId);
    if (optionalEvents.isPresent()) {
      Events events = optionalEvents.get();
      if (eventRequest.getNameEvent() != null && !eventRequest.getNameEvent().isEmpty()) {
        events.setNameEvent(eventRequest.getNameEvent());
      }
      if (eventRequest.getDescriptionEvent() != null && !eventRequest.getDescriptionEvent()
          .isEmpty()) {
        events.setDescription(eventRequest.getDescriptionEvent());
      }
      if (eventRequest.getEventImage() != null && !eventRequest.getEventImage().isEmpty()) {
        events.setEventImage(eventRequest.getEventImage());
      }

      if (eventRequest.getNameCategory() != null && !eventRequest.getNameCategory().isEmpty()) {
        Categories categories = categoriesRepository.findByName(
                eventRequest.getNameCategory()) //tim kiem ten danh muc lay ra
            .get();
        events.setCategories(categories); // set vao event
      }

      Locations locations = events.getLocations(); //lay ra locations
      if (eventRequest.getNameLocation() != null && !eventRequest.getNameLocation()
          .isEmpty()) { // kiem tra xem co nhap gi de update hay ko
        locations.setName(eventRequest.getNameLocation()); //set name location ben locations
        events.setLocations(locations); //set locations ben events
      }

      if (eventRequest.getDescriptionAddress() != null && !eventRequest.getDescriptionAddress()
          .isEmpty()) {
        locations.setDescription(
            eventRequest.getDescriptionAddress()); //set description ben locations
        events.setLocations(locations); //set locations ben events
      }

      if (eventRequest.getAddress() != null && !eventRequest.getAddress().isEmpty()) {
        locations.setAddress(eventRequest.getAddress()); // set address ben locations
        events.setLocations(locations); //set locations ben events
      }

      locationsRepository.save(locations); // luu du lieu update location ben locations

      if (eventRequest.getStartTime() != null) {
        events.setStartTime(eventRequest.getStartTime());
      }

      if (eventRequest.getEndTime() != null) {
        events.setEndTime(eventRequest.getEndTime());
      }
      events.setUpdateTime(LocalDateTime.now());
      return eventsRepository.save(events);
    } else {
      throw new NotFoundException("no id event update admin");
    }
  }

  @Override
  @Cacheable(value = "eventRegistration")
  public Page<EventRegistrationResponse> getAllEventRegistration(Pageable pageable) {
    Page<Events> events = eventsRepository.findAll(pageable);
    Page<EventRegistrationResponse> eventRegistrationResponses = events.map(events1 -> {
      EventRegistrationResponse response = iEventsMapper.convertEventRegistrationMapper(events1);
      response.setNumberFeedback((long) events1.getFeedbacks().size());
      response.setNumberRegistration((long) events1.getRegistrations().size());
      return response;
    });
    return eventRegistrationResponses;
  }

  @Override
  public Specification<Events> createEventSpec(EventSearchRequest search) {
    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      Join<Events, Categories> eventJoinCategories = root.join("categories", JoinType.INNER);
      Join<Events, Locations> eventsLocationsJoin = root.join("locations", JoinType.INNER);

      if (search.getNameEvent() != null && !search.getNameEvent().isEmpty()) {
        predicates.add(
            criteriaBuilder.like(root.get("nameEvent"), "%" + search.getNameEvent() + "%"));
      }

      if (search.getNameCategory() != null && !search.getNameCategory().isEmpty()) {
        predicates.add(
            criteriaBuilder.like(eventJoinCategories.get("name"), search.getNameCategory()));
      }

      if (search.getNameLocation() != null && !search.getNameLocation().isEmpty()) {
        predicates.add(
            criteriaBuilder.like(eventsLocationsJoin.get("name"), search.getNameLocation()));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  @Override
  public Page<EventsResponse> search(Pageable pageable, EventSearchRequest eventSearchRequest) {
    Specification<Events> spec = Specification.where(createEventSpec(eventSearchRequest));
    Page<Events> events = eventsRepository.findAll(spec, pageable);
    Page<EventsResponse> eventsResponses = events.map(events1 -> {
      EventsResponse eventsResponse = iEventsMapper.convertEntityEventsMapper(events1);
      eventsResponse.setNumberFeedback((long) events1.getFeedbacks().size());
      return eventsResponse;
    });
    return eventsResponses;
  }
}

