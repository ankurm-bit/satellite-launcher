package com.satellite.launcher.service;


import com.satellite.launcher.util.CustomerSatellite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerSatelliteService {
    ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> createCustomerSatellite(CustomerSatellite customerSatellite);
    ResponseEntity<List<com.satellite.launcher.entity.CustomerSatellite>> retrieveCustomerSatellite();
    ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> retrieveCustomerSatelliteById(String customerSatelliteId);
    ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> updateCustomerSatellite(String id , CustomerSatellite customerSatellite);
    ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> partialUpdateCustomerSatellite(String id , CustomerSatellite customerSatellite);
    ResponseEntity<String> deleteCustomerSatellite(String customerSatelliteId);
}
