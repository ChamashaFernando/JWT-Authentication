package lk.chamasha.jwt.authentication.repository;


import lk.chamasha.jwt.authentication.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findById(Long id);  // Explicit declaration
}
