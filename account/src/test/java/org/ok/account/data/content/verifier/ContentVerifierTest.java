package org.ok.account.data.content.verifier;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.ok.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentVerifierTest extends AccountTest {

    @Autowired
    private ContentVerifier contentVerifier;

    @Test
    void shouldFindLoaded() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> loaded = contentVerifier.findLoaded(items);
        assertEquals(IterableUtils.toList(loaded), items);
    }

    @Test
    void shouldFindNotLoaded() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> loaded = contentVerifier.findNotLoaded(items);
        assertTrue(IterableUtils.toList(loaded).isEmpty());
    }
}