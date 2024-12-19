package com.klef.jfsd.springboot;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeepAliveService {

    private final RestTemplate restTemplate = new RestTemplate();

    // URLs of services to ping
    private final String eurekaUrl = "https://fusionhub-backend-eurekaserver.onrender.com/actuator/health";
    private final List<String> serviceUrls = List.of(
        "https://fusionhub-backend-adminservice.onrender.com//actuator/health",
        "https://fusionhub-backend-studentservice.onrender.com/actuator/health",
        "https://fusionhub-backend-apigateway.onrender.com/actuator/health"
    );

    // Ping Eureka every 4 minutes
    @Scheduled(fixedRate = 240000) // 240,000 ms = 4 minutes
    public void pingEureka() {
        try {
            restTemplate.getForObject(eurekaUrl, String.class);
            System.out.println("Pinged Eureka: " + eurekaUrl);
        } catch (Exception e) {
            System.err.println("Failed to ping Eureka: " + e.getMessage());
        }
    }

    // Ping other services every 5 minutes
    @Scheduled(fixedRate = 300000) // 300,000 ms = 5 minutes
    public void pingOtherServices() {
        for (String url : serviceUrls) {
            try {
                restTemplate.getForObject(url, String.class);
                System.out.println("Pinged: " + url);
            } catch (Exception e) {
                System.err.println("Failed to ping: " + url + ". Error: " + e.getMessage());
            }
        }
    }
}
