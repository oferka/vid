package org.ok.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static org.ok.controller.Paths.ACCOUNT_PATH;
import static org.ok.controller.Paths.RANDOM_ID_PATH;
import static org.ok.controller.ServiceAddresses.ACCOUNT_SERVICE_ADDRESS;

@Service
@Slf4j
@Profile({"random-account-id-provider", "default"})
public class RandomAccountIdProvider implements AccountIdProvider {

    private final RestTemplate restTemplate;

    public RandomAccountIdProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public @NotNull Long get() {
        ResponseEntity<Long> response = restTemplate.getForEntity(ACCOUNT_SERVICE_ADDRESS + ACCOUNT_PATH + RANDOM_ID_PATH, Long.class);
        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus == HttpStatus.OK) {
            Long accountId = response.getBody();
            log.info("Random account Id: {}", accountId);
            return accountId;
        }
        throw new RuntimeException(format("Failed to retrieve random account Id. Status code is: %s, and reason is: %s", httpStatus.value(), httpStatus.getReasonPhrase()));
    }
}
