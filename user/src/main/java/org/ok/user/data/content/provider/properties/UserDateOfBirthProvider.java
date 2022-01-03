package org.ok.user.data.content.provider.properties;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UserDateOfBirthProvider {

    public ZonedDateTime get() {
        long daysAgo = RandomUtils.nextLong(5000, 80000);
        return ZonedDateTime.now().minusDays(daysAgo);
    }
}
