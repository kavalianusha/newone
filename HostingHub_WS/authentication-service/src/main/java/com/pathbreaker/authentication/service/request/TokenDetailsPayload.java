package com.pathbreaker.authentication.service.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TokenDetailsPayload {

    private String clientId;

    private Long accessExpirationInMin;

    private Long refreshExpirationInMin;

    private Date createdDate;

    private Date lastUpdatedDate;

}