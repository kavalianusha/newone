package com.pathbreaker.authentication.service.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TokenDetailsResponse {

    private Long id;

    private String clientId;

    private Long accessExpirationInMin;

    private Long refreshExpirationInMin;

    private LocalDate createdDate;

    private LocalDate lastUpdatedDate;
}
