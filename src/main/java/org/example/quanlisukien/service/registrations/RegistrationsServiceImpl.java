package org.example.quanlisukien.service.registrations;

import java.time.LocalDateTime;
import java.util.Optional;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Registrations;
import org.example.quanlisukien.data.request.RegistrationRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationsServiceImpl implements RegistrationService {


  private final RegistrationsRepository registrationsRepository;

  private final AccountRepository accountRepository;

  private final EventsRepository eventsRepository;

  @Autowired
  public RegistrationsServiceImpl(RegistrationsRepository registrationsRepository,
      AccountRepository accountRepository, EventsRepository eventsRepository) {
    this.registrationsRepository = registrationsRepository;
    this.accountRepository = accountRepository;
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Registrations registrationEvent(RegistrationRequest registrationRequest) {
    Optional<Events> eventsOptional = eventsRepository.findById(registrationRequest.getEventId());
    Optional<Account> accountOptional = accountRepository.findById(
        registrationRequest.getUserId());
    if (eventsOptional.isPresent() && accountOptional.isPresent()) {
      Events events = eventsOptional.get();
      Account account = accountOptional.get();
      Registrations registrations = new Registrations();

      registrations.setFullName(registrationRequest.getFullName());
      registrations.setEmail(registrationRequest.getEmail());
      registrations.setPhoneNumber(registrationRequest.getPhoneNumber());
      registrations.setJoinEvent(registrationRequest.getJoinEvent());
      registrations.setFeelEvent(registrationRequest.getFeelEvent());
      registrations.setAccount(account);
      registrations.setEvents(events);
      registrations.setUsername(account.getUsername());
      registrations.setDateTime(LocalDateTime.now());

      try{
        return registrationsRepository.save(registrations);
      } catch (DataAccessException ex) {
        throw new InternalServerException("no save database");
      }
    } else {
      throw new NotFoundException("no event_id or user_id");
    }
  }
}
