package org.ok.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

import static org.ok.integration.Paths.ACCOUNT_PATH;
import static org.ok.integration.Paths.RANDOM_ID_PATH;
import static org.ok.integration.ServiceAddresses.ACCOUNT_SERVICE_ADDRESS;

@Service
@Slf4j
@Profile({"random-rest-template-account-id-provider"})
public class RandomRestTemplateAccountIdProvider extends RandomAccountIdProvider {

    private final RestTemplate restTemplate;

    public RandomRestTemplateAccountIdProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public @NotNull Long get() {
        ResponseEntity<Long> response = restTemplate.getForEntity(ACCOUNT_SERVICE_ADDRESS + ACCOUNT_PATH + RANDOM_ID_PATH, Long.class);
        return get(response);
    }
}
