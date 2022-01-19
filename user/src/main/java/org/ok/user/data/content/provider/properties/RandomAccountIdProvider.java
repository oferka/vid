package org.ok.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;

@Slf4j
public abstract class RandomAccountIdProvider implements AccountIdProvider {

    protected @NotNull Long get(@NotNull ResponseEntity<Long> response) {
        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus == HttpStatus.OK) {
            Long accountId = response.getBody();
            log.info("Random account Id: {}", accountId);
            return accountId;
        }
        throw new RuntimeException(format("Failed to retrieve random account Id. Status code is: %s, and reason is: %s", httpStatus.value(), httpStatus.getReasonPhrase()));
    }
}
