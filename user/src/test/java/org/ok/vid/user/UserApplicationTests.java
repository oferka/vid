package org.ok.vid.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
@Slf4j
public class UserApplicationTests {

    @Test
    void contextLoads() {
        log.info("Application loaded successfully");
    }
}
