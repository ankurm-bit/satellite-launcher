package com.satellite.launcher.repository;

import com.satellite.launcher.entity.SatelliteLauncher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteLauncherRepository extends JpaRepository<SatelliteLauncher, String> {
}
