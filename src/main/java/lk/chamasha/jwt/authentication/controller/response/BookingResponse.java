package lk.chamasha.jwt.authentication.controller.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {
    private Long id;
    private String serviceName;
    private LocalDate bookingDate;
    private Long customerId;
    private String customerName;
}
