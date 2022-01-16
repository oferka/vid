package org.ok.user.data.content.health;

import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
public class ContentHealthIndicatorTest extends UserTest {

    @Autowired
    private ContentHealthIndicator contentHealthIndicator;

    @Test
    void shouldBeHealthy() {
        Health health = contentHealthIndicator.health();
        Status status = health.getStatus();
        assertEquals(Status.UP, status);
    }
}
