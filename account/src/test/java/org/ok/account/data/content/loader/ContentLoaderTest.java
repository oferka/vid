package org.ok.account.data.content.loader;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.ok.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ContentLoaderTest extends AccountTest {

    @Autowired
    private ContentLoader contentLoader;

    @Test
    void shouldEnsureContentLoaded() {
        Iterable<Account> loaded = contentLoader.ensureContentLoaded();
        assertFalse(IterableUtils.toList(loaded).isEmpty());
    }
}