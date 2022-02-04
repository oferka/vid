package org.ok.vid.service_registry.mapper.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.ok.vid.service_registry.mapper.model.RegisteredService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.ok.vid.integration.Paths.SERVICE_REGISTRY_MAPPER_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = SERVICE_REGISTRY_MAPPER_PATH)
public class RegisteredServiceController {

    private final DiscoveryClient discoveryClient;

    @GetMapping
    @Timed(value = "RegisteredServiceController.findAll.timer", description = "Timer for registered service findAll endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "RegisteredServiceController.findAll.counter", description = "Counter for registered service findAll endpoint")
    @Operation(summary = "Find all registered services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registered services successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = RegisteredService.class)))}),
            @ApiResponse(responseCode = "400", description = "Failed to find accounts", content = @Content) }
    )
    public @NotNull ResponseEntity<List<RegisteredService>> findAll() {
        return ResponseEntity.ok(getRegisteredServices());
    }

    @GetMapping(value = "{serviceId}")
    @Timed(value = "RegisteredServiceController.findByServiceId.timer", description = "Timer for registered service findByServiceId endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "RegisteredServiceController.findByServiceId.counter", description = "Counter for registered service findByServiceId endpoint")
    @Operation(summary = "Find a registered service by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registered service successfully found by Id", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = RegisteredService.class))}),
            @ApiResponse(responseCode = "404", description = "Registered service with specified Id was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find Registered service by Id", content = @Content) })
    public @NotNull ResponseEntity<RegisteredService> findByServiceId(@Parameter(description = "The Id of the registered service to be found") @PathVariable("serviceId") @NotNull String serviceId) {
        Optional<RegisteredService> item = getRegisteredService(serviceId);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private @NotNull List<RegisteredService> getRegisteredServices() {
        List<RegisteredService> items = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            items.add(new RegisteredService(service, serviceInstances));
        }
        return items;
    }

    private Optional<RegisteredService> getRegisteredService(@NotNull String serviceId) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        if(!serviceInstances.isEmpty()) {
            return Optional.of(new RegisteredService(serviceId, serviceInstances));
        }
        return Optional.empty();
    }
}
