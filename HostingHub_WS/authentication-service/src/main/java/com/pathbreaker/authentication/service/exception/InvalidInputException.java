package com.pathbreaker.authentication.service.exception;

import com.pathbreaker.services.filters.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends BaseException {

    public InvalidInputException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}


