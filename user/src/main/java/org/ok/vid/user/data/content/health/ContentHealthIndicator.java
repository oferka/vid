package org.ok.vid.user.data.content.health;

import lombok.RequiredArgsConstructor;
import org.ok.vid.user.service.UserService;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("content")
@RequiredArgsConstructor
@ConditionalOnEnabledHealthIndicator("content")
public class ContentHealthIndicator implements HealthIndicator {

    private final UserService userService;

    @Override
    public Health health() {
        Health.Builder status;
        try {
            long count = userService.count();
            status = (count > 0)?Health.up():Health.down();
            status.withDetail("number_of_users", count);
        }
        catch (Exception e) {
            status = Health.down(e);
        }
        return status.build();
    }
}
