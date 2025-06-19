package lk.chamasha.jwt.authentication.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private int rating; // 1 - 5

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
