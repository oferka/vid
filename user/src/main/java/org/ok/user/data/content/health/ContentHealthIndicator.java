package org.ok.user.data.content.health;

import org.ok.user.service.UserService;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("content")
@ConditionalOnEnabledHealthIndicator("content")
public class ContentHealthIndicator implements HealthIndicator {

    private final UserService userService;

    public ContentHealthIndicator(UserService userService) {
        this.userService = userService;
    }

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
