package org.example.quanlisukien.service.feedbacks;

import org.example.quanlisukien.data.entity.Feedbacks;
import org.example.quanlisukien.data.request.FeedbackRequest;

public interface FeedbackService {

  Feedbacks createFeedback(FeedbackRequest feedbackRequest); //method them feedback event;

  Feedbacks updateFeedback(Long feedback_id,FeedbackRequest feedbackRequest); //method update feedbacks

}
