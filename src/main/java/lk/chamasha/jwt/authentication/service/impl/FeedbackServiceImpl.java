
package lk.chamasha.jwt.authentication.service.impl;

import lk.chamasha.jwt.authentication.controller.request.FeedbackRequest;
import lk.chamasha.jwt.authentication.controller.response.FeedbackResponse;
import lk.chamasha.jwt.authentication.model.Customer;
import lk.chamasha.jwt.authentication.model.Feedback;
import lk.chamasha.jwt.authentication.repository.CustomerRepository;
import lk.chamasha.jwt.authentication.repository.FeedbackRepository;
import lk.chamasha.jwt.authentication.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CustomerRepository customerRepository;

    @Override
    public FeedbackResponse createFeedback(FeedbackRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        Feedback feedback = Feedback.builder()
                .message(request.getMessage())
                .rating(request.getRating())
                .customer(customer)
                .build();

        Feedback saved = feedbackRepository.save(feedback);
        return mapToResponse(saved);
    }

    @Override
    public FeedbackResponse getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
        return mapToResponse(feedback);
    }

    @Override
    public List<FeedbackResponse> getAllFeedback() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackResponse updateFeedback(Long id, FeedbackRequest request) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        feedback.setMessage(request.getMessage());
        feedback.setRating(request.getRating());
        feedback.setCustomer(customer);

        Feedback updated = feedbackRepository.save(feedback);
        return mapToResponse(updated);
    }

    @Override
    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
        feedbackRepository.delete(feedback);
    }

    private FeedbackResponse mapToResponse(Feedback feedback) {
        FeedbackResponse res = new FeedbackResponse();
        res.setId(feedback.getId());
        res.setMessage(feedback.getMessage());
        res.setRating(feedback.getRating());
        res.setCustomerId(feedback.getCustomer().getId());
        res.setCustomerName(feedback.getCustomer().getName());
        return res;
    }
}
