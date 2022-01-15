package org.ok.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
@Profile({"random-account-id-provider", "default"})
public class RandomAccountIdProvider implements AccountIdProvider {

    private final RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public RandomAccountIdProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public @NotNull Long get() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("ACCOUNT");
//        ServiceInstance instance = instances.get(0);
//        URI uri = instance.getUri();
//        ResponseEntity<Long> response = restTemplate.getForEntity(uri + "/api/rest/account/random/id", Long.class);

//        ResponseEntity<Long> response = restTemplate.getForEntity("http://localhost:62029/api/rest/account/random/id", Long.class);
        ResponseEntity<Long> response = restTemplate.getForEntity("http://account/api/rest/account/random/id", Long.class);

        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus == HttpStatus.OK) {
            Long accountId = response.getBody();
            log.info("Random account Id: {}", accountId);
            return accountId;
        }
        throw new RuntimeException(format("Failed to retrieve random account Id. Status code is: %s, and reason is: %s", httpStatus.value(), httpStatus.getReasonPhrase()));
    }
}
