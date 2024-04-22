package org.example.quanlisukien.service.registrations;

import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Registrations;
import org.example.quanlisukien.data.request.RegistrationRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IRegistrationMapper;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.RegistrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationsServiceImpl implements RegistrationService {


  private final RegistrationsRepository registrationsRepository;

  private final AccountRepository accountRepository;

  private final EventsRepository eventsRepository;

  private final IRegistrationMapper iRegistrationMapper;

  @Autowired
  public RegistrationsServiceImpl(RegistrationsRepository registrationsRepository,
      AccountRepository accountRepository, EventsRepository eventsRepository,
      IRegistrationMapper iRegistrationMapper) {
    this.registrationsRepository = registrationsRepository;
    this.accountRepository = accountRepository;
    this.eventsRepository = eventsRepository;
    this.iRegistrationMapper = iRegistrationMapper;
  }

  @Override
  @CacheEvict(value = "eventRegistration",allEntries = true)
  public Registrations registrationEvent(RegistrationRequest registrationRequest) {
    Events events = eventsRepository.findById(registrationRequest.getEventId())
        .orElseThrow(() -> new NotFoundException("no event_id"));
    Account account = accountRepository.findById(registrationRequest.getUserId())
        .orElseThrow(() -> new NotFoundException("no user_id"));

    Registrations registrations = iRegistrationMapper.convertToEntity(registrationRequest);

    registrations.setAccount(account);
    registrations.setEvents(events);
    registrations.setUsername(account.getUsername());
    try {
      return registrationsRepository.save(registrations);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database");
    }
  }

  @Override
  @CacheEvict(value = "eventRegistration",allEntries = true)
  public void deleteRegistration(Long registrationsId) {
    if (registrationsRepository.existsById(registrationsId)) {
      registrationsRepository.deleteById(registrationsId);
    } else {
      throw new NotFoundException("no registrations found");
    }
  }
}
