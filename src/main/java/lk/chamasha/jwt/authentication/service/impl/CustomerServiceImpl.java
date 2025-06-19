package lk.chamasha.jwt.authentication.service.impl;

import lk.chamasha.jwt.authentication.controller.request.CustomerRequest;
import lk.chamasha.jwt.authentication.controller.response.CustomerResponse;
import lk.chamasha.jwt.authentication.exception.UserAlreadyRegisteredException;
import lk.chamasha.jwt.authentication.exception.UserNotFoundException;
import lk.chamasha.jwt.authentication.model.Customer;
import lk.chamasha.jwt.authentication.repository.CustomerRepository;
import lk.chamasha.jwt.authentication.security.ApplicationConfig;
import lk.chamasha.jwt.authentication.security.JwtService;
import lk.chamasha.jwt.authentication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) throws UserAlreadyRegisteredException {
        customerRepository.findByUsername(customerRequest.getUsername())
                .ifPresent(c -> {
                    throw new UserAlreadyRegisteredException("Customer already registered with username: " + customerRequest.getUsername());
                });

        customerRepository.findByEmail(customerRequest.getEmail())
                .ifPresent(c -> {
                    throw new UserAlreadyRegisteredException("Customer already registered with email: " + customerRequest.getEmail());
                });

        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setUsername(customerRequest.getUsername());
        customer.setPassword(applicationConfig.passwordEncoder().encode(customerRequest.getPassword()));
        customer.setRole(customerRequest.getRole());

        customerRepository.save(customer);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", customer.getUsername());
        extraClaims.put("password", customer.getPassword());
        extraClaims.put("roles", List.of("ROLE_" + customer.getRole().name()));
        extraClaims.put("name", customer.getName());
        extraClaims.put("email", customer.getEmail());
        extraClaims.put("phone", customer.getPhone());

        UserDetails userDetails = User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .roles(customer.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails, extraClaims);

        return CustomerResponse.builder()
                .token(token)
                .role(customer.getRole())
                .build();
    }

    @Override
    public CustomerResponse login(CustomerRequest customerRequest) throws UserNotFoundException {
        Customer customer = customerRepository.findByUsername(customerRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + customerRequest.getUsername()));

        if (!applicationConfig.passwordEncoder().matches(customerRequest.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", customer.getUsername());
        extraClaims.put("password", customer.getPassword());
        extraClaims.put("roles", List.of("ROLE_" + customer.getRole().name()));
        extraClaims.put("name", customer.getName());
        extraClaims.put("email", customer.getEmail());
        extraClaims.put("phone", customer.getPhone());

        UserDetails userDetails = User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .roles(customer.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails, extraClaims);

        return CustomerResponse.builder()
                .id(customer.getId())
                .token(token)
                .role(customer.getRole())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .username(customer.getUsername())
                .build();
    }

    @Override
    public CustomerResponse getById(Long id) throws UserNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found with id: " + id));

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .username(customer.getUsername())
                .role(customer.getRole())
                .build();
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .username(customer.getUsername())
                .role(customer.getRole())
                .build()).toList();
    }
}
