package com.satellite.launcher.service;

import org.springframework.http.ResponseEntity;

public interface DataLoadSatelliteService {
    ResponseEntity<String> loadSatelliteData();
    ResponseEntity<String> loadCustomerSatelliteData();
}
