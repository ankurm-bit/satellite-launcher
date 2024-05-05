package com.satellite.launcher.controller;

import com.satellite.launcher.entity.SatelliteLauncher;
import com.satellite.launcher.service.SatelliteLauncherService;
import com.satellite.launcher.util.Launcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/satellite/api")
public class SatelliteLauncherController {
    private static final Logger logger = LoggerFactory.getLogger(SatelliteLauncherController.class);

    private final SatelliteLauncherService satelliteLauncherService;

    @Autowired
    public SatelliteLauncherController(SatelliteLauncherService satelliteLauncherService) {
        this.satelliteLauncherService = satelliteLauncherService;
    }

    @PostMapping("/launcher")
    public ResponseEntity<String> saveSatelliteLauncherInfo(@RequestBody Launcher launcher) {
        logger.info("Saving satellite launcher info");
        return satelliteLauncherService.saveSatelliteLauncherInfo(launcher);
    }

    @GetMapping("/launcher")
    public ResponseEntity<List<SatelliteLauncher>> retrieveSatelliteLauncherInfo() {
        logger.info("Retrieving satellite launcher info");
        return satelliteLauncherService.getSatelliteLauncherInfo();
    }

    @GetMapping("/launcher/{id}")
    public ResponseEntity<SatelliteLauncher> retrieveSatelliteLauncherInfo(@PathVariable String id) {
        logger.info("Retrieving satellite launcher info by id: {}", id);
        return satelliteLauncherService.getSatelliteLauncherInfoById(id);
    }

    @PatchMapping("/launcher/{id}")
    public ResponseEntity<SatelliteLauncher> updateSatelliteLauncherInfo(@PathVariable String id ,@RequestBody Launcher launcher) {
        logger.info("Updating satellite launcher info by id: {}", id);
        return satelliteLauncherService.partialUpdateSatelliteLauncherInfo(id, launcher);
    }

    @DeleteMapping("/launcher/{id}")
    public ResponseEntity<String> deleteSatelliteLauncherInfo(@PathVariable String id) {
        logger.info("Deleting satellite launcher info by id: {}", id);
        return satelliteLauncherService.deleteSatelliteLauncherInfo(id);
    }
}
