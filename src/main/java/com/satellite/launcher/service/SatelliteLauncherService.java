package com.satellite.launcher.service;

import com.satellite.launcher.entity.SatelliteLauncher;
import com.satellite.launcher.util.Launcher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SatelliteLauncherService {
    ResponseEntity<String> saveSatelliteLauncherInfo(Launcher launcher);
    ResponseEntity<List<SatelliteLauncher>> getSatelliteLauncherInfo();
    ResponseEntity<SatelliteLauncher> getSatelliteLauncherInfoById(String launcherId);
    ResponseEntity<String> deleteSatelliteLauncherInfo(String launcherId);
    ResponseEntity<SatelliteLauncher> partialUpdateSatelliteLauncherInfo(String launcherId ,Launcher launcher);
}
