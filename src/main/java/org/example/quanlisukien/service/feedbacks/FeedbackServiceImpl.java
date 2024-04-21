package org.example.quanlisukien.service.feedbacks;

import java.time.LocalDateTime;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.request.FeedbackRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.IFeedbackMapper;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbacksRepository feedbacksRepository;

  private final EventsRepository eventsRepository;

  private final AccountRepository accountRepository;

  private final IFeedbackMapper iFeedbackMapper;


  @Autowired
  public FeedbackServiceImpl(FeedbacksRepository feedbacksRepository,
      EventsRepository eventsRepository, AccountRepository accountRepository,
      IFeedbackMapper iFeedbackMapper) {
    this.feedbacksRepository = feedbacksRepository;
    this.eventsRepository = eventsRepository;
    this.accountRepository = accountRepository;
    this.iFeedbackMapper = iFeedbackMapper;
  }

  @Override
  @CacheEvict(value = "eventRegistration", allEntries = true)
  public Feedbacks createFeedback(FeedbackRequest feedbackRequest) {
    Account account = accountRepository.findById(feedbackRequest.getUserId())
        .orElseThrow(() -> new NotFoundException("no id account"));
    Events events = eventsRepository.findById(feedbackRequest.getEventId())
        .orElseThrow(() -> new NotFoundException("no id event"));

    Feedbacks feedbacks = iFeedbackMapper.toFeedbacks(
        feedbackRequest); //mapper feedbackRequest sang feedbacks

    feedbacks.setAccount(account);
    feedbacks.setEvents(events);
    feedbacks.setUsername(account.getUsername());
    try {
      return feedbacksRepository.save(feedbacks);
    } catch (DataAccessException ex) {
      throw new InternalServerException("no save database");
    }
  }

  @Override
  public Feedbacks updateFeedback(Long feedbackId, FeedbackRequest feedbackRequest) {

    Feedbacks feedbacks = feedbacksRepository.findById(feedbackId)
        .orElseThrow(() -> new NotFoundException("No ID feedback"));

    //check user_id va event_id co quyen sua feedback hay ko
    if (feedbacks.getEvents().getEventId().equals(feedbackRequest.getEventId())
        && feedbacks.getAccount().getUserId().equals(feedbackRequest.getUserId())) {

      if (feedbackRequest.getFeedbackContent() != null && !feedbackRequest.getFeedbackContent()
          .isEmpty()) {
        feedbacks.setFeedbackContent(feedbackRequest.getFeedbackContent());
        feedbacks.setUpdateTime(LocalDateTime.now());
      }

      if (feedbackRequest.getFeedbackImage() != null && !feedbackRequest.getFeedbackImage()
          .isEmpty()) {
        feedbacks.setFeedbackImage(feedbackRequest.getFeedbackImage());
        feedbacks.setUpdateTime(LocalDateTime.now());
      }
      return feedbacksRepository.save(feedbacks);
    }
    throw new NotFoundException("no update feedback");
  }

  @Override
  public void deleteFeedback(Long feedbackId) {

    if (feedbacksRepository.existsById(feedbackId)) {
      feedbacksRepository.deleteById(feedbackId);
    } else {
      throw new NotFoundException("no id delete feedback");
    }
  }
}
