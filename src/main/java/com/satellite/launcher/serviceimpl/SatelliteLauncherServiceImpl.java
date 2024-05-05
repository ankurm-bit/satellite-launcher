package com.satellite.launcher.serviceimpl;

import com.satellite.launcher.entity.SatelliteLauncher;
import com.satellite.launcher.exception.SatelliteLauncherNotFoundException;
import com.satellite.launcher.repository.SatelliteLauncherRepository;
import com.satellite.launcher.service.SatelliteLauncherService;
import com.satellite.launcher.util.Launcher;
import com.satellite.launcher.util.Launchers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SatelliteLauncherServiceImpl implements SatelliteLauncherService {
    private final SatelliteLauncherRepository satelliteLauncherRepository;

    @Autowired
    public SatelliteLauncherServiceImpl(SatelliteLauncherRepository satelliteLauncherRepository) {
        this.satelliteLauncherRepository = satelliteLauncherRepository;
    }

    @Override
    public ResponseEntity<String> saveSatelliteLauncherInfo(Launcher launcher) {
        log.info("saveSatelliteLauncherInfo completed");
        SatelliteLauncher satelliteLauncher = new SatelliteLauncher();
        BeanUtils.copyProperties(launcher, satelliteLauncher);
        satelliteLauncherRepository.save(satelliteLauncher);
        return ResponseEntity.ok().body("satellite launcher saved");
    }

    @Override
    public ResponseEntity<List<SatelliteLauncher>> getSatelliteLauncherInfo() {
        log.info("getSatelliteLauncherInfo completed");
        List<SatelliteLauncher> satelliteLaunchers = satelliteLauncherRepository.findAll();
        if (satelliteLaunchers.isEmpty()) {
            throw new SatelliteLauncherNotFoundException("Satellite launcher not found");
        }else
            return ResponseEntity.ok().body(satelliteLaunchers);
    }

    @Override
    public ResponseEntity<SatelliteLauncher> getSatelliteLauncherInfoById(String launcherId) {
        log.info("getSatelliteLauncherInfoById completed");
        Optional<SatelliteLauncher> satelliteLauncherOptional = satelliteLauncherRepository.findById(launcherId);
        if (satelliteLauncherOptional.isPresent()) {
            return ResponseEntity.ok().body(satelliteLauncherOptional.get());
        }else
            throw new SatelliteLauncherNotFoundException("Satellite launcher not found");
    }

    @Override
    public ResponseEntity<String> deleteSatelliteLauncherInfo(String launcherId) {
        log.info("deleteSatelliteLauncherInfo completed");
        satelliteLauncherRepository.deleteById(launcherId);
        return ResponseEntity.ok().body("satellite launcher deleted");
    }

    @Override
    public ResponseEntity<SatelliteLauncher> partialUpdateSatelliteLauncherInfo(String launcherId, Launcher launcher) {
        log.info("partialUpdateSatelliteLauncherInfo completed");
        Optional<SatelliteLauncher> satelliteLauncherOptional = satelliteLauncherRepository.findById(launcherId);
        if (satelliteLauncherOptional.isPresent()) {
            SatelliteLauncher satelliteLauncher = satelliteLauncherOptional.get();
            BeanUtils.copyProperties(launcher, satelliteLauncher);
            satelliteLauncherRepository.save(satelliteLauncher);
            log.info("Satellite launcher partially updated");
            return ResponseEntity.ok().body(satelliteLauncher);
        }
        else
            throw new SatelliteLauncherNotFoundException("Satellite launcher not found");

    }
}
