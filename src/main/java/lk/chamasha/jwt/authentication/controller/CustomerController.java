package lk.chamasha.jwt.authentication.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.chamasha.jwt.authentication.controller.request.CustomerRequest;
import lk.chamasha.jwt.authentication.controller.response.CustomerResponse;
import lk.chamasha.jwt.authentication.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @PostMapping("/register")
    public CustomerResponse create(@RequestBody CustomerRequest request) {
        return customerService.create(request);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @PostMapping("/login")
    public CustomerResponse login(@RequestBody CustomerRequest request) {
        return customerService.login(request);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @RolesAllowed({"ADMIN", "CUSTOMER"})
    @GetMapping
    public List<CustomerResponse> getAll() {
        return customerService.getAll();
    }
}
