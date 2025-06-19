package lk.chamasha.jwt.authentication.service.impl;

import lk.chamasha.jwt.authentication.controller.request.BookingRequest;
import lk.chamasha.jwt.authentication.controller.response.BookingResponse;
import lk.chamasha.jwt.authentication.model.Booking;
import lk.chamasha.jwt.authentication.model.Customer;
import lk.chamasha.jwt.authentication.repository.BookingRepository;
import lk.chamasha.jwt.authentication.repository.CustomerRepository;
import lk.chamasha.jwt.authentication.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    @Override
    public BookingResponse createBooking(BookingRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        Booking booking = Booking.builder()
                .serviceName(request.getServiceName())
                .bookingDate(request.getBookingDate())
                .customer(customer)
                .build();

        Booking saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapToResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse updateBooking(Long id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        booking.setServiceName(request.getServiceName());
        booking.setBookingDate(request.getBookingDate());
        booking.setCustomer(customer);

        Booking updated = bookingRepository.save(booking);
        return mapToResponse(updated);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        BookingResponse res = new BookingResponse();
        res.setId(booking.getId());
        res.setServiceName(booking.getServiceName());
        res.setBookingDate(booking.getBookingDate());
        res.setCustomerId(booking.getCustomer().getId());
        res.setCustomerName(booking.getCustomer().getName());
        return res;
    }
}
