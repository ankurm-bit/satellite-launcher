package com.satellite.launcher.controller;

import com.satellite.launcher.service.CustomerSatelliteService;
import com.satellite.launcher.util.CustomerSatellite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/satellite/api")
public class CustomerSatelliteController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSatelliteController.class);

    private final CustomerSatelliteService customerSatelliteService;

    @Autowired
    public CustomerSatelliteController(CustomerSatelliteService customerSatelliteService) {
        this.customerSatelliteService = customerSatelliteService;
    }

    @PostMapping("/customer-satellite")
    public ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> createCustomerSatellite(@RequestBody CustomerSatellite customerSatellite) {
        logger.info("Create customer satellite");
        return customerSatelliteService.createCustomerSatellite(customerSatellite);
    }

    @GetMapping("/customer-satellite")
    public ResponseEntity<List<com.satellite.launcher.entity.CustomerSatellite>> retrieveCustomerSatelliteInfo() {
        logger.info("Retrieve customer satellite info");
        return customerSatelliteService.retrieveCustomerSatellite();
    }

    @GetMapping("/customer-satellite/{id}")
    public ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> retrieveCustomerSatellite(@PathVariable String id) {
        logger.info("Retrieve customer satellite");
        return customerSatelliteService.retrieveCustomerSatelliteById(id);
    }

    @PutMapping("/customer-satellite/{id}")
    public ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> updateCustomerSatellite(@PathVariable String id, @RequestBody CustomerSatellite customerSatellite) {
        logger.info("Update customer satellite");
        return customerSatelliteService.updateCustomerSatellite(id, customerSatellite);
    }

    @PatchMapping("/customer-satellite/{id}")
    public ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> partialUpdateCustomerSatellite(@PathVariable String id, @RequestBody CustomerSatellite customerSatellite) {
        logger.info("Partial update customer satellite");
        return customerSatelliteService.partialUpdateCustomerSatellite(id, customerSatellite);
    }

    @DeleteMapping("/customer-satellite/{id}")
    public ResponseEntity<String> deleteCustomerSatellite(@PathVariable String id) {
        logger.info("Delete customer satellite");
        return customerSatelliteService.deleteCustomerSatellite(id);
    }

}
