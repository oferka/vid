package org.ok.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.ok.account.client.AccountClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;

@Service
@Slf4j
@Profile({"random-feign-account-id-provider", "default"})
public class RandomFeignAccountIdProvider implements AccountIdProvider {

    private final AccountClient accountClient;

    public RandomFeignAccountIdProvider(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public @NotNull Long get() {
        ResponseEntity<Long> response = accountClient.findRandomId();
        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus == HttpStatus.OK) {
            Long accountId = response.getBody();
            log.info("Random account Id: {}", accountId);
            return accountId;
        }
        throw new RuntimeException(format("Failed to retrieve random account Id. Status code is: %s, and reason is: %s", httpStatus.value(), httpStatus.getReasonPhrase()));
    }
}
