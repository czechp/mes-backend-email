package com.bispol.emailservicebackendspring.configuration;

import com.bispol.emailservicebackendspring.exception.BadRequestException;
import com.bispol.emailservicebackendspring.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice()
class ControllerAdviceConfiguration {
    @ExceptionHandler({UsernameNotFoundException.class})
    ResponseEntity usernameNotFoundExceptionHandler(RuntimeException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    ResponseEntity badExceptionHandler(RuntimeException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    ResponseEntity notFoundExceptionHandler(RuntimeException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    private ResponseEntity buildResponse(HttpStatus status, String message) {
        HashMap<String, String> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", message);
        return new ResponseEntity(body, status);
    }
}
