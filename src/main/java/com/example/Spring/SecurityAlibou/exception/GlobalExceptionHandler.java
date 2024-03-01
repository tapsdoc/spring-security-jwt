package com.example.Spring.SecurityAlibou.exception;

import com.example.Spring.SecurityAlibou.commons.HttpResponse;
import com.example.Spring.SecurityAlibou.exception.domain.EmailExistsException;
import com.example.Spring.SecurityAlibou.exception.domain.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException() {
        return createHttpResponse(HttpStatus.FORBIDDEN,"Username / password incorrect. Please try again");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException exception) {
        return createHttpResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<?> emailExistsException(EmailExistsException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
            new HttpResponse(
                httpStatus.value(),
                httpStatus,
                message
            ), httpStatus
        );
    }
}
