package org.example.quanlisukien.service.feedbacks;

import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.request.FeedbackRequest;

public interface FeedbackService {

  Feedbacks createFeedback(FeedbackRequest feedbackRequest); //method them feedback event;

  Feedbacks updateFeedback(Long feedbackId,FeedbackRequest feedbackRequest); //method update feedbacks

  void deleteFeedback (Long feedbackId); //method xoa 1 feedback
}
