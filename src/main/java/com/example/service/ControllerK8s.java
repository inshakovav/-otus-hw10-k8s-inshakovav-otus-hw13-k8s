package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

@RestController
@Slf4j
public class ControllerK8s {

    private Map stringStorage = new HashMap<String, String>();
    private int storageIndex = 0;

    private Integer sleepMilliseconds = 0;

    @GetMapping("/version")
    String getVersion() {
        return "v9.0.11";
    }

    @GetMapping
    StatusDTO getDefault() {
        return getStatus("from root");
    }

    @PostMapping("/sleep")
    String toggleSleep(@RequestBody SleepDto sleepDto) {
        sleepMilliseconds = sleepDto.getMilliseconds();
        return "Set sleep period, milliseconds=" + sleepMilliseconds;
    }

    @GetMapping("/startup")
    StatusDTO getStartupProbe() {
        log.info("StartupProbe");
        return getStatus("StartupProbe");
    }

    @GetMapping("/liveness")
    StatusDTO getLivenessProbe() throws InterruptedException {
        log.info("LivenessProbe");
//        log.info("Sleep milliseconds=" + sleepMilliseconds);
//        sleep(sleepMilliseconds);
        return getStatus("LivenessProbe");
    }

    @GetMapping("/readiness")
    StatusDTO getReadinessProbe() throws InterruptedException {
//        log.info("ReadinessProbe");
        log.info("ReadinessProbe Sleep milliseconds=" + sleepMilliseconds);
        sleep(sleepMilliseconds);
        return getStatus("ReadinessProbe");
    }

    @GetMapping("/health")
    StatusDTO getHealth() {
        return getStatus("from health");
    }

    @GetMapping("/healthcheck")
    StatusDTO getHealthCheck() {
        return getStatus("from healthcheck");
    }

    private StatusDTO getStatus(String prefix) {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        StatusDTO statusDTO = StatusDTO.builder()
                .status(prefix + " hostname=" + hostName)
                .build();

        return statusDTO;
    }

    @PostMapping("/load-cpu")
    String loadCpu() {
        long startAt = System.currentTimeMillis();
        long current = startAt;
        log.info("Current time={}", startAt);

        long ticks = 0;
        for (;;ticks++) {
            current = System.currentTimeMillis();
            if((current - startAt) >= 5000) {
                break;
            }
        }

        return "Thread sleep for " + (current - startAt) + " milliseconds" + " count=" + ticks;
    }

    @PostMapping("/allocate-memory")
    String allocateMemory() {
        int quantityItems= 1000;
        for (int i = 0; i < quantityItems; i++, storageIndex++) {
            String[] array = new String[10000];
            stringStorage.put(String.valueOf(storageIndex + 1), array);
        }
        return "Allocate memory, items=" + stringStorage.size();
    }
}
