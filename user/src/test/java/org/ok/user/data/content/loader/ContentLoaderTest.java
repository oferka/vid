package org.ok.user.data.content.loader;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ContentLoaderTest  extends UserTest {

    @Autowired
    private ContentLoader contentLoader;

    @Test
    void shouldEnsureContentLoaded() {
        Iterable<User> loaded = contentLoader.ensureContentLoaded();
        assertFalse(IterableUtils.toList(loaded).isEmpty());
    }
}
