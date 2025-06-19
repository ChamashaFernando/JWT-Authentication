package lk.chamasha.jwt.authentication.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.chamasha.jwt.authentication.controller.request.BookingRequest;
import lk.chamasha.jwt.authentication.controller.response.BookingResponse;
import lk.chamasha.jwt.authentication.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @RolesAllowed("CUSTOMER")
    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @RolesAllowed("CUSTOMER")
    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @RolesAllowed( "CUSTOMER")
    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @RolesAllowed( "CUSTOMER")
    @PutMapping("/{id}")
    public BookingResponse updateBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        return bookingService.updateBooking(id, request);
    }

    @RolesAllowed("CUSTOMER")
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
