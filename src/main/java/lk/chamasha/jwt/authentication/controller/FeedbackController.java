package lk.chamasha.jwt.authentication.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.chamasha.jwt.authentication.controller.request.FeedbackRequest;
import lk.chamasha.jwt.authentication.controller.response.FeedbackResponse;
import lk.chamasha.jwt.authentication.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @RolesAllowed({"ADMIN","CUSTOMER"})
    @PostMapping
    public FeedbackResponse createFeedback(@RequestBody FeedbackRequest request) {
        return feedbackService.createFeedback(request);
    }

    @RolesAllowed({"ADMIN","CUSTOMER"})
    @GetMapping("/{id}")
    public FeedbackResponse getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @RolesAllowed({"ADMIN","CUSTOMER"})
    @GetMapping
    public List<FeedbackResponse> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @RolesAllowed({"ADMIN","CUSTOMER"})
    @PutMapping("/{id}")
    public FeedbackResponse updateFeedback(@PathVariable Long id, @RequestBody FeedbackRequest request) {
        return feedbackService.updateFeedback(id, request);
    }

    @RolesAllowed({"ADMIN","CUSTOMER"})
    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
