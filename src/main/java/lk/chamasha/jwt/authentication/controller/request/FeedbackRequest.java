package lk.chamasha.jwt.authentication.controller.request;

import lombok.Data;

@Data
public class FeedbackRequest {
    private String message;
    private int rating;
    private Long customerId;
}
