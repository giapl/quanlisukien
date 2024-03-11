package org.example.quanlisukien.controller.feedbacks;

import jakarta.validation.Valid;
import org.example.quanlisukien.data.request.FeedbackRequest;
import org.example.quanlisukien.service.feedbacks.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedbacksController {

  private final FeedbackService feedbackService;

  @Autowired
  public FeedbacksController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {
    return ResponseEntity.ok(feedbackService.createFeedback(feedbackRequest));
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateFeedback(@RequestParam Long feedback_id, @RequestBody FeedbackRequest feedbackRequest) {
    return ResponseEntity.ok(feedbackService.updateFeedback(feedback_id, feedbackRequest));
  }
  @DeleteMapping("/delete/{feedback_id}")
  public ResponseEntity<?> deleteFeedback(@PathVariable Long feedback_id) {
    feedbackService.deleteFeedback(feedback_id);
    return ResponseEntity.ok("delete by id successful");
  }
}
