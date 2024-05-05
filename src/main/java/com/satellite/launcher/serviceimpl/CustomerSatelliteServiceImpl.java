package com.satellite.launcher.serviceimpl;

import com.satellite.launcher.entity.CustomerSatellite;
import com.satellite.launcher.exception.CustomerSatelliteNotFoundException;
import com.satellite.launcher.repository.CustomerSatelliteRepository;
import com.satellite.launcher.service.CustomerSatelliteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerSatelliteServiceImpl implements CustomerSatelliteService {


    private final CustomerSatelliteRepository customerSatelliteRepository;

    @Autowired
    public CustomerSatelliteServiceImpl(CustomerSatelliteRepository customerSatelliteRepository) {
        this.customerSatelliteRepository = customerSatelliteRepository;
    }

    @Override
    public ResponseEntity<com.satellite.launcher.entity.CustomerSatellite> createCustomerSatellite(com.satellite.launcher.util.CustomerSatellite customerSatellite) {
       CustomerSatellite cs = new CustomerSatellite();
       BeanUtils.copyProperties(customerSatellite, cs);
       customerSatelliteRepository.save(cs);
       log.info("CustomerSatellite saved {}", cs.getId());
       return new ResponseEntity<>(cs, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CustomerSatellite>> retrieveCustomerSatellite() {
        log.info("CustomerSatellite retrieved");
        List<CustomerSatellite> customerSatelliteList = customerSatelliteRepository.findAll();
        if (customerSatelliteList.isEmpty()) {
            throw new CustomerSatelliteNotFoundException("CustomerSatellite not found");
        }
        return new ResponseEntity<>(customerSatelliteList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerSatellite> retrieveCustomerSatelliteById(String customerSatelliteId) {
        log.info("CustomerSatellite retrieved {}", customerSatelliteId);
        Optional<CustomerSatellite> customerSatellite = customerSatelliteRepository.findById(customerSatelliteId);
        if (customerSatellite.isPresent()) {
            return new ResponseEntity<>(customerSatellite.get(), HttpStatus.OK);
        }else
            throw new CustomerSatelliteNotFoundException("CustomerSatellite not found");
    }

    @Override
    public ResponseEntity<CustomerSatellite> updateCustomerSatellite(String id, com.satellite.launcher.util.CustomerSatellite customerSatellite) {
       log.info("CustomerSatellite updated {}", customerSatellite.getId());
        return getResponseEntity(id, customerSatellite);

    }

    @Override
    public ResponseEntity<CustomerSatellite> partialUpdateCustomerSatellite(String id, com.satellite.launcher.util.CustomerSatellite customerSatellite) {
        log.info("CustomerSatellite partially updated {}", customerSatellite.getId());
        return getResponseEntity(id, customerSatellite);
    }

    private ResponseEntity<CustomerSatellite> getResponseEntity(String id, com.satellite.launcher.util.CustomerSatellite customerSatellite) {
        Optional<CustomerSatellite> customerSatelliteOptional = customerSatelliteRepository.findById(id);
        if (customerSatelliteOptional.isPresent()) {
            CustomerSatellite satellite = customerSatelliteOptional.get();

            if(Objects.nonNull(customerSatellite.getId()) &&
                    !customerSatellite.getId().isEmpty()) {
                satellite.setId(customerSatellite.getId());
            }

            if(Objects.nonNull(customerSatellite.getMass()) &&
                    !customerSatellite.getMass().isEmpty()) {
                satellite.setMass(customerSatellite.getMass());
            }

            if(Objects.nonNull(customerSatellite.getLaunchDate())) {
                satellite.setLaunchDate(customerSatellite.getLaunchDate());
            }

            if(Objects.nonNull(customerSatellite.getCountry()) &&
                    !customerSatellite.getCountry().isEmpty()) {
                satellite.setCountry(customerSatellite.getCountry());
            }

            if(Objects.nonNull(customerSatellite.getLauncher()) &&
                    !customerSatellite.getLauncher().isEmpty()) {
                satellite.setLauncher(customerSatellite.getLauncher());
            }

            customerSatelliteRepository.save(satellite);
            log.info("CustomerSatellite saved {}", satellite.getId());
            return new ResponseEntity<>(satellite, HttpStatus.OK);
        }else
            throw new CustomerSatelliteNotFoundException("CustomerSatellite not found");
    }

    @Override
    public ResponseEntity<String> deleteCustomerSatellite(String customerSatelliteId) {
        log.info("CustomerSatellite deleted {}", customerSatelliteId);
        customerSatelliteRepository.deleteById(customerSatelliteId);
        return new ResponseEntity<>("Deleted "+customerSatelliteId, HttpStatus.OK);
    }
}
