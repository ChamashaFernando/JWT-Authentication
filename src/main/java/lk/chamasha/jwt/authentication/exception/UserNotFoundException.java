package lk.chamasha.jwt.authentication.exception;

import lk.chamasha.jwt.authentication.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
