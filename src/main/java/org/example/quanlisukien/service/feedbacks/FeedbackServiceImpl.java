package org.example.quanlisukien.service.feedbacks;

import java.time.LocalDateTime;
import java.util.Optional;
import org.example.quanlisukien.data.entity.Account;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.request.FeedbackRequest;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.repository.AccountRepository;
import org.example.quanlisukien.repository.EventsRepository;
import org.example.quanlisukien.repository.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbacksRepository feedbacksRepository;

  private final EventsRepository eventsRepository;

  private final AccountRepository accountRepository;

  @Autowired
  public FeedbackServiceImpl(FeedbacksRepository feedbacksRepository,
      EventsRepository eventsRepository, AccountRepository accountRepository) {
    this.feedbacksRepository = feedbacksRepository;
    this.eventsRepository = eventsRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public Feedbacks createFeedback(FeedbackRequest feedbackRequest) {
    Optional<Account> account = accountRepository.findById(feedbackRequest.getUserId());
    Optional<Events> events = eventsRepository.findById(feedbackRequest.getEventId());
    if (account.isPresent() && events.isPresent()) {
      Account account1 = account.get();
      Events events1 = events.get();

      Feedbacks feedbacks = new Feedbacks();
      feedbacks.setFeedbackContent(feedbackRequest.getFeedbackContent());
      feedbacks.setFeedbackImage(feedbackRequest.getFeedbackImage());
      feedbacks.setAccount(account1);
      feedbacks.setEvents(events1);
      feedbacks.setDateTime(LocalDateTime.now());
      feedbacks.setUpdateTime(LocalDateTime.now());

      feedbacks.setUsername(account1.getUsername());
      try {
        return feedbacksRepository.save(feedbacks);
      } catch (DataAccessException ex) {
        throw new InternalServerException("no save database");
      }
    } else {
      throw new NotFoundException("no user_id and event_id");
    }
  }

  @Override
  public Feedbacks updateFeedback(Long feedbackId, FeedbackRequest feedbackRequest) {
    Optional<Feedbacks> feedbacksOptional = feedbacksRepository.findById(feedbackId);
    if (feedbacksOptional.isPresent()) {
      Feedbacks feedbacks = feedbacksOptional.get();

      //check user_id va event_id co quyen sua feedback hay ko
      if (feedbacks.getEvents().getEventId().equals(feedbackRequest.getEventId())
          && feedbacks.getAccount().getUserId().equals(feedbackRequest.getUserId())) {

        if (feedbackRequest.getFeedbackContent() != null) {
          feedbacks.setFeedbackContent(feedbackRequest.getFeedbackContent());
        }

        if (feedbackRequest.getFeedbackImage() != null) {
          feedbacks.setFeedbackImage(feedbackRequest.getFeedbackImage());
        }

        feedbacks.setUpdateTime(LocalDateTime.now());
        return feedbacksRepository.save(feedbacks);
      }
    }
    throw new NotFoundException("no update feedback");
  }

  @Override
  public void deleteFeedback(Long feedbackId) {

    if(feedbacksRepository.existsById(feedbackId)) {
      feedbacksRepository.deleteById(feedbackId);
    } else {
      throw new NotFoundException("no id delete feedback");
    }
  }
}
