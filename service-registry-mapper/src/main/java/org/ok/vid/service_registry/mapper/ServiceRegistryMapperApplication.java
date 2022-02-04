package org.ok.vid.service_registry.mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceRegistryMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryMapperApplication.class, args);
    }
}
