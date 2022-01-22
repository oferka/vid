package org.ok.vid.user.data.content.provider.properties;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Profile("local-account-id-provider")
public class LocalAccountIdProvider implements AccountIdProvider {

    @Override
    public @NotNull Long get() {
        return RandomUtils.nextLong(1, 100);
    }
}
