package lk.chamasha.jwt.authentication.service;


import lk.chamasha.jwt.authentication.controller.request.FeedbackRequest;
import lk.chamasha.jwt.authentication.controller.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    FeedbackResponse createFeedback(FeedbackRequest request);
    FeedbackResponse getFeedbackById(Long id);
    List<FeedbackResponse> getAllFeedback();
    FeedbackResponse updateFeedback(Long id, FeedbackRequest request);
    void deleteFeedback(Long id);
}
