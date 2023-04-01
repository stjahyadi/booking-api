package com.hackerrank.sample.interceptor;

import com.hackerrank.sample.exception.HotelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class HotelExceptionHandler {
    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Problem handleHotelNotFound(HotelNotFoundException ex){
        return new Problem(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Instant.now());
    }

}
