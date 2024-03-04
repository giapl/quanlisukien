package org.example.quanlisukien.service.events;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.entity.Locations;
import org.example.quanlisukien.data.request.EventRequest;
import org.example.quanlisukien.data.response.EventGetIdResponse;
import org.example.quanlisukien.data.response.EventsResponse;
import org.example.quanlisukien.data.response.FeedbackResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IEventsMapper;
import org.example.quanlisukien.mapper.IFeedbackMapper;
import org.example.quanlisukien.repository.CategoriesRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.FeedbacksRepository;
import org.example.quanlisukien.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventsServiceImpl implements EventsService {

  private final CategoriesRepository categoriesRepository;

  private final EventsRepository eventsRepository;

  private final LocationsRepository locationsRepository;

  private final IEventsMapper iEventsMapper;

  private final FeedbacksRepository feedbacksRepository;

  private final IFeedbackMapper feedbackMapper;


  @Autowired
  public EventsServiceImpl(CategoriesRepository categoriesRepository,
      EventsRepository eventsRepository, LocationsRepository locationsRepository,
      IEventsMapper iEventsMapper, FeedbacksRepository feedbacksRepository,
      IFeedbackMapper feedbackMapper) {
    this.categoriesRepository = categoriesRepository;
    this.eventsRepository = eventsRepository;
    this.locationsRepository = locationsRepository;
    this.iEventsMapper = iEventsMapper;
    this.feedbacksRepository = feedbacksRepository;
    this.feedbackMapper = feedbackMapper;
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
  public void deleteByIdEvent(Long event_id) {
    if (eventsRepository.existsById(event_id)) { //kiem tra xem id co ton tai ko
      eventsRepository.deleteById(event_id);
    } else {
      throw new NotFoundException("no id delete admin");
    }
  }

  @Override
  public Events updateByIdEvents(Long event_id, EventRequest eventRequest) {
    Optional<Events> optionalEvents = eventsRepository.findById(event_id);
    if (optionalEvents.isPresent()) {
      Events events = optionalEvents.get();
      if (eventRequest.getName_event() != null) {
        events.setName_event(eventRequest.getName_event());
      }
      if (eventRequest.getDescription_event() != null) {
        events.setDescription(eventRequest.getDescription_event());
      }
      if (eventRequest.getEvent_image() != null) {
        events.setEvent_image(eventRequest.getEvent_image());
      }

      if (eventRequest.getName_category() != null) {
        Categories categories = categoriesRepository.findByName(
                eventRequest.getName_category()) //tim kiem ten danh muc lay ra
            .get();
        events.setCategories(categories); // set vao event
      }

      Locations locations = events.getLocations(); //lay ra locations
      if (eventRequest.getName_location() != null) { // kiem tra xem co nhap gi de update hay ko
        locations.setName(eventRequest.getName_location()); //set name location ben locations
        events.setLocations(locations); //set locations ben events
      }

      if (eventRequest.getDescription_address() != null) {
        locations.setDescription(
            eventRequest.getDescription_address()); //set description ben locations
        events.setLocations(locations); //set locations ben events
      }

      if (eventRequest.getAddress() != null) {
        locations.setAddress(eventRequest.getAddress()); // set address ben locations
        events.setLocations(locations); //set locations ben events
      }

      locationsRepository.save(locations); // luu du lieu update location ben locations

      if (eventRequest.getStart_time() != null) {
        events.setStart_time(eventRequest.getStart_time());
      }

      if (eventRequest.getEnd_time() != null) {
        events.setEnd_time(eventRequest.getEnd_time());
      }
      events.setUpdateTime(LocalDateTime.now());
      return eventsRepository.save(events);
    } else {
      throw new NotFoundException("no id event update admin");
    }
  }


  @Override
  public EventGetIdResponse getById(Long id) {
    Optional<Events> optionalEvents = eventsRepository.findById(id);

    List<Feedbacks> feedbacks = feedbacksRepository.findByEventsEvent_id(id);

    List<FeedbackResponse> feedbackResponses = feedbackMapper.convertFeedbackMapper(feedbacks);

    if (optionalEvents.isPresent()) {
      EventGetIdResponse eventGetIdResponse = iEventsMapper.convertEventMapper(
          optionalEvents.get());
      eventGetIdResponse.setFeedback(feedbackResponses);
      return eventGetIdResponse;
    } else {
      throw new NotFoundException("no id");
    }
  }

  @Override
  public Page<EventsResponse> getAll(int ofSize, Pageable pageable) {
    PageRequest pageRequest = PageRequest.of(ofSize, 10)
        .withSort(Sort.by("dateTime")
            .descending()); //trang bat dau vaf co dinh 5 ban ghi moi trang vaf sap xep giam dan
    Page<Events> events = eventsRepository.findAll(pageRequest);
    Page<EventsResponse> eventsResponses = events.map(events1 -> {
      EventsResponse eventsResponse = iEventsMapper.convertEntityEventsMapper(events1);
      eventsResponse.setNumberFeedback(
          (long) events1.getFeedbacks().size()); // map lai vs set number
      return eventsResponse;
    });
    return eventsResponses;
  }

  @Override
  public Page<EventsResponse> getByName_event(int ofSize, String name_event, Pageable pageable) {
    Page<Events> events = eventsRepository.findByName_event(name_event,
        PageRequest.of(ofSize, 5)
            .withSort(Sort.by("event_id")
                .descending())); //tim  name_event vs moi trang 5 ban ghi vs sap xe theo giam dan event_id
    Page<EventsResponse> eventsResponses = events.map(events1 -> {
      EventsResponse eventsResponse = iEventsMapper.convertEntityEventsMapper(events1);
      eventsResponse.setNumberFeedback((long) events1.getFeedbacks().size()); //map lai va setNumber
      return eventsResponse;
    });
    return eventsResponses;
  }

  @Override
  public Page<EventsResponse> getByCategoryName(int offSize, String name_category,
      Pageable pageable) {
    Page<Events> events = eventsRepository.findAllByCategories(name_category,
        PageRequest.of(offSize, 5));
    Page<EventsResponse> eventsResponses = events.map(events1 -> {
      EventsResponse eventsResponse = iEventsMapper.convertEntityEventsMapper(events1);
      eventsResponse.setNumberFeedback((long) events1.getFeedbacks().size()); //map lai va setNumber
      return eventsResponse;
    });
    return eventsResponses;
  }

}
