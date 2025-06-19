package lk.chamasha.jwt.authentication.repository;

import lk.chamasha.jwt.authentication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUsername(String username);
    @Query("SELECT c.role FROM Customer c WHERE c.username = :username")
    String findRoleByUsername(String username);
}
