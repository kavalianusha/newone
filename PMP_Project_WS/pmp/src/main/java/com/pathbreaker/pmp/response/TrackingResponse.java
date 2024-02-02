package com.pathbreaker.pmp.response;

import lombok.Data;

@Data
public class TrackingResponse {
    private String month;

    private String revenueType;

    private String source;

    private int completion;

    private String nameOfSource;

    private String resourceAssigned;

    private int revenue;

    private String date;
}
