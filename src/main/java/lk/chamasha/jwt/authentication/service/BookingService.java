package lk.chamasha.jwt.authentication.service;



import lk.chamasha.jwt.authentication.controller.request.BookingRequest;
import lk.chamasha.jwt.authentication.controller.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getAllBookings();
    BookingResponse updateBooking(Long id, BookingRequest request);
    void deleteBooking(Long id);
}
