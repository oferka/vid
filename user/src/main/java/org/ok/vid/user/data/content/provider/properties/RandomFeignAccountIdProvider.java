package org.ok.vid.user.data.content.provider.properties;

import lombok.extern.slf4j.Slf4j;
import org.ok.vid.account.client.AccountClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
@Profile({"random-feign-account-id-provider", "default"})
public class RandomFeignAccountIdProvider extends RandomAccountIdProvider {

    private final AccountClient accountClient;

    public RandomFeignAccountIdProvider(AccountClient accountClient) {
        this.accountClient = accountClient;
    }

    @Override
    public @NotNull Long get() {
        ResponseEntity<Long> response = accountClient.findRandomId();
        return get(response);
    }
}
