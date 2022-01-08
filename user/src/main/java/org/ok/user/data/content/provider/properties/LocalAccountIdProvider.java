package org.ok.user.data.content.provider.properties;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local-account-id-provider")
public class LocalAccountIdProvider implements AccountIdProvider {

    @Override
    public Long get() {
        return RandomUtils.nextLong(1, 100);
    }
}
