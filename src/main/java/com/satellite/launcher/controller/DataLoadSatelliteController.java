package com.satellite.launcher.controller;

import com.satellite.launcher.service.DataLoadSatelliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/satellite/api/resource")
public class DataLoadSatelliteController {
    private static final Logger logger = LoggerFactory.getLogger(DataLoadSatelliteController.class);

    private final  DataLoadSatelliteService dataLoadSatelliteService;

    @Autowired
    public DataLoadSatelliteController(DataLoadSatelliteService dataLoadSatelliteService) {
        this.dataLoadSatelliteService = dataLoadSatelliteService;
    }

    @GetMapping("/launchers")
    public ResponseEntity<String> loadSatellite() {
        logger.info("Loading Satellite Data");
        return dataLoadSatelliteService.loadSatelliteData();
    }

    @GetMapping("/customer-satellite")
    public ResponseEntity<String> loadCustomerSatellite() {
        logger.info("Loading Customer Satellite Data");
        return dataLoadSatelliteService.loadCustomerSatelliteData();
    }
}
