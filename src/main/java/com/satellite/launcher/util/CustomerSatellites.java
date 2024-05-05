package com.satellite.launcher.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSatellites {
    @JsonProperty("customer_satellites")
    private List<CustomerSatellite> customerSatellites;
}
