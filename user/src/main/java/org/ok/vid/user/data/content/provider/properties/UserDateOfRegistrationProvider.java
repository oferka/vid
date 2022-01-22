package org.ok.vid.user.data.content.provider.properties;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UserDateOfRegistrationProvider {

    public ZonedDateTime get() {
        long daysAgo = RandomUtils.nextLong(3, 1000);
        return ZonedDateTime.now().minusDays(daysAgo);
    }
}
