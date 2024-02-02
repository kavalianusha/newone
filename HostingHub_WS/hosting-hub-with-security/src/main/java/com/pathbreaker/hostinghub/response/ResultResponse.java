package com.pathbreaker.hostinghub.response;

// Import Lombok's Data annotation for generating boilerplate code
import lombok.Data;

@Data
// Define the class for handling host domain map requests
public class ResultResponse {

    public String jwtToken;

    public String result;

    public String refreshToken;

    public String role;
}
