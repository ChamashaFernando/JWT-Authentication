package lk.chamasha.jwt.authentication.controller.response;

import lombok.Data;

@Data
public class FeedbackResponse {
    private Long id;
    private String message;
    private int rating;
    private Long customerId;
    private String customerName;
}
