package com.pathbreaker.payslip.exception;

import com.pathbreaker.services.filters.exceptions.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Exceptions extends BaseException {

    private String httpStatus;

    private String message;

    public Exceptions(HttpStatus httpStatus, String message){
        super();
        this.httpStatus = httpStatus.name();
        this.message = message;

    }


}
