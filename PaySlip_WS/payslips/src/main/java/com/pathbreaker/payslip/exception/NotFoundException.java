package com.pathbreaker.payslip.exception;

import com.pathbreaker.services.filters.exceptions.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends BaseException {

    private String status;
    private String message;
    public NotFoundException(HttpStatus httpStatus, String message) {
        super();
        this.status = httpStatus.name();
        this.message = message;
    }

}
