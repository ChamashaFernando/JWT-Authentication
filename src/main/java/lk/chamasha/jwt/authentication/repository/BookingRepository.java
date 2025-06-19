package lk.chamasha.jwt.authentication.repository;


import lk.chamasha.jwt.authentication.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long id);  // This already exists from JpaRepository but explicitly shown
}
