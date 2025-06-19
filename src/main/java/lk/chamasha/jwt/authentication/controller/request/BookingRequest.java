package lk.chamasha.jwt.authentication.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private String serviceName;
    private LocalDate bookingDate;
    private Long customerId;
}
