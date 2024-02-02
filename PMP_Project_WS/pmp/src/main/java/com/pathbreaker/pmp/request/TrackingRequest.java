package com.pathbreaker.pmp.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TrackingRequest {

    private String month;

    private String revenueType;

    private String source;

    private int completion;

    private String nameOfSource;

    private String resourceAssigned;

    private int revenue;

    private String date;
}