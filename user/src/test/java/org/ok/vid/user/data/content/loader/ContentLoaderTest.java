package org.ok.vid.user.data.content.loader;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.ok.vid.user.UserTest;
import org.ok.vid.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
public class ContentLoaderTest  extends UserTest {

    @Autowired
    private ContentLoader contentLoader;

    @Test
    void shouldEnsureContentLoaded() {
        Iterable<User> loaded = contentLoader.ensureContentLoaded();
        assertFalse(IterableUtils.toList(loaded).isEmpty());
    }
}
