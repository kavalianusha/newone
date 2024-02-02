package com.pathbreaker.pmp.exception;

import com.pathbreaker.services.filters.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class Exceptions extends BaseException {
    public Exceptions(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}