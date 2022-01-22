package org.ok.vid.account.data.content.health;

import lombok.RequiredArgsConstructor;
import org.ok.vid.account.service.AccountService;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("content")
@ConditionalOnEnabledHealthIndicator("content")
@RequiredArgsConstructor
public class ContentHealthIndicator implements HealthIndicator {

    private final AccountService accountService;

    @Override
    public Health health() {
        Health.Builder status;
        try {
            long count = accountService.count();
            status = (count > 0)?Health.up():Health.down();
            status.withDetail("number_of_accounts", count);
        }
        catch (Exception e) {
            status = Health.down(e);
        }
        return status.build();
    }
}
