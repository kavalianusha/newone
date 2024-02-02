package com.example.efilingbazaar.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResultCount {

    private String result;
    private LocalDate date;
}
