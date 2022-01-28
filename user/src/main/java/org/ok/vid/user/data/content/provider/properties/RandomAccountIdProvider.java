package org.ok.vid.user.data.content.provider.properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;

public abstract class RandomAccountIdProvider implements AccountIdProvider {

    protected @NotNull Long get(@NotNull ResponseEntity<Long> response) {
        HttpStatus httpStatus = response.getStatusCode();
        if(httpStatus == HttpStatus.OK) {
            return response.getBody();
        }
        throw new RuntimeException(format("Failed to retrieve random account Id. Status code is: %s, and reason is: %s", httpStatus.value(), httpStatus.getReasonPhrase()));
    }
}
