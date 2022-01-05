package org.ok.account.data.content.health;

import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContentHealthIndicatorTest extends AccountTest {

    @Autowired
    private ContentHealthIndicator contentHealthIndicator;

    @Test
    void shouldBeHealthy() {
        Health health = contentHealthIndicator.health();
        Status status = health.getStatus();
        assertEquals(Status.UP, status);
    }
}