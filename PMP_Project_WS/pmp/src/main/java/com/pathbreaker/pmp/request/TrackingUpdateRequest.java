package com.pathbreaker.pmp.request;

import lombok.Data;

@Data
public class TrackingUpdateRequest {

    private String month;

    private String revenueType;

    private String source;

    private int completion;

    private String nameOfSource;

    private String resourceAssigned;

    private int revenue;

    private String date;
}