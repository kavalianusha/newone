package com.pathbreaker.pmp.exception;

import com.pathbreaker.services.filters.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
