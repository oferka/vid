package org.ok.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
public class UserApplicationTests {

    @Test
    void contextLoads() {
    }
}
