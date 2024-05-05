package com.satellite.launcher.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSatellite {
    @Id
    private String id;
    private String country;
    private Date launchDate;
    private String mass;
    private String launcher;
}
