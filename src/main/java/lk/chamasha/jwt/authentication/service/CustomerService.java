package lk.chamasha.jwt.authentication.service;



import lk.chamasha.jwt.authentication.controller.request.CustomerRequest;
import lk.chamasha.jwt.authentication.controller.response.CustomerResponse;
import lk.chamasha.jwt.authentication.exception.UserAlreadyRegisteredException;
import lk.chamasha.jwt.authentication.exception.UserNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest) throws UserAlreadyRegisteredException;

    CustomerResponse login(CustomerRequest customerAuthRequest) throws UserNotFoundException;

    CustomerResponse getById(Long id) throws UserNotFoundException;

    List<CustomerResponse> getAll();
}