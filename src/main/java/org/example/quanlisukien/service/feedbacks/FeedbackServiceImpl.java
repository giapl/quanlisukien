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
    Optional<Account> account = accountRepository.findById(feedbackRequest.getUser_id());
    Optional<Events> events = eventsRepository.findById(feedbackRequest.getEvent_id());
    if (account.isPresent() && events.isPresent()) {
      Account account1 = account.get();
      Events events1 = events.get();

      Feedbacks feedbacks = new Feedbacks();
      feedbacks.setFeedback_content(feedbackRequest.getFeedback_content());
      feedbacks.setFeedback_image(feedbackRequest.getFeedback_image());
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
  public Feedbacks updateFeedback(Long feedback_id, FeedbackRequest feedbackRequest) {
    Optional<Feedbacks> feedbacksOptional = feedbacksRepository.findById(feedback_id);
    if (feedbacksOptional.isPresent()) {
      Feedbacks feedbacks = feedbacksOptional.get();

      //check user_id va event_id co quyen sua feedback hay ko
      if (feedbacks.getEvents().getEvent_id().equals(feedbackRequest.getEvent_id())
          && feedbacks.getAccount().getUser_id().equals(feedbackRequest.getUser_id())) {

        if (feedbackRequest.getFeedback_content() != null) {
          feedbacks.setFeedback_content(feedbackRequest.getFeedback_content());
        }

        if (feedbackRequest.getFeedback_image() != null) {
          feedbacks.setFeedback_image(feedbackRequest.getFeedback_image());
        }

        feedbacks.setUpdateTime(LocalDateTime.now());
        return feedbacksRepository.save(feedbacks);
      }
    }
    throw new NotFoundException("no update feedback");
  }

  @Override
  public void deleteFeedback(Long feedback_id) {

    if(feedbacksRepository.existsById(feedback_id)) {
      feedbacksRepository.deleteById(feedback_id);
    } else {
      throw new NotFoundException("no id delete feedback");
    }
  }
}
