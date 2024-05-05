package com.satellite.launcher.repository;

import com.satellite.launcher.entity.CustomerSatellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSatelliteRepository extends JpaRepository<CustomerSatellite, String> {
}
