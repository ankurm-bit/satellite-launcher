package com.satellite.launcher.serviceimpl;

import com.satellite.launcher.entity.CustomerSatellite;
import com.satellite.launcher.entity.SatelliteLauncher;
import com.satellite.launcher.repository.CustomerSatelliteRepository;
import com.satellite.launcher.repository.SatelliteLauncherRepository;
import com.satellite.launcher.service.DataLoadSatelliteService;
import com.satellite.launcher.util.CustomerSatellites;
import com.satellite.launcher.util.Launchers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class DataLoadSatelliteServiceImpl implements DataLoadSatelliteService {

    private final SatelliteLauncherRepository satelliteLauncherRepository;
    private final CustomerSatelliteRepository  customerSatelliteRepository;

    private final WebClient webClient;

    @Autowired
    public DataLoadSatelliteServiceImpl(SatelliteLauncherRepository satelliteLauncherRepository, CustomerSatelliteRepository customerSatelliteRepository, WebClient webClient) {
        this.satelliteLauncherRepository = satelliteLauncherRepository;
        this.customerSatelliteRepository = customerSatelliteRepository;
        this.webClient = webClient;
    }

    @Override
    public ResponseEntity<String> loadSatelliteData() {
        webClient.get().uri("/launchers")
                .retrieve().bodyToMono(Launchers.class).subscribe(launchers -> {
                    Flux.fromIterable(launchers.getLaunchers()).subscribe(launcher -> {
                        satelliteLauncherRepository.save(new SatelliteLauncher(launcher.getId(), null, null));
                        log.info("Satellite launcher saved {}", launcher.getId());
                    });
                });
        log.info("getAndSaveInitialData completed");
        return ResponseEntity.ok().body("data fetched and saved from api ");
    }

    @Override
    public ResponseEntity<String> loadCustomerSatelliteData() {
        webClient.get().uri("/customer_satellites")
                .retrieve().bodyToMono(CustomerSatellites.class).subscribe(satellites -> {
                    Flux.fromIterable(satellites.getCustomerSatellites()).subscribe(satellite -> {
                        CustomerSatellite cse = new CustomerSatellite();

                        BeanUtils.copyProperties(satellite, cse);
                        customerSatelliteRepository.save(cse);
                        log.info("CustomerSatellite saved {}", cse.getId());
                    });
                });
        log.info("getAndSaveCustomerSatelliteData completed");
        return new ResponseEntity<>("DATA is fetched and persisted!!", HttpStatus.OK);
    }
}
