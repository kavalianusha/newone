package com.pathbreaker.authentication.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TokenDetails")
public class TokenDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private Long accessExpirationInMin;

    private Long refreshExpirationInMin;

    private LocalDate createdDate;

    private LocalDate lastUpdatedDate;
}
