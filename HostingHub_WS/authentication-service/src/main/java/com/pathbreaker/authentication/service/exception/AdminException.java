// Define the package where this class belongs
package com.pathbreaker.authentication.service.exception;

// Import necessary classes
import com.pathbreaker.services.filters.exceptions.BaseException;
import org.springframework.http.HttpStatus;

// Extend the RuntimeException class
public class AdminException  extends BaseException
{
    public AdminException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
