// Define the package where this class belongs
package com.pathbreaker.hostinghub.exception;

// Import necessary classes
import com.pathbreaker.hostinghub.response.ResultResponse;

// Extend the RuntimeException class
public class AdminException extends RuntimeException {

    // A unique identifier for serialization
    private static final long serialVersionUID = 1L;

    // Constructor that takes a message as input
    public AdminException(String message) {
        super(message);
    }

    // Constructor that takes a ResultResponse as input
    public AdminException(ResultResponse result) {
        // TODO Auto-generated constructor stub
    }

}
