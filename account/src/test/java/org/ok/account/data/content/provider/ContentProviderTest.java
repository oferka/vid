package org.ok.account.data.content.provider;

import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.ok.account.model.Account;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ContentProviderTest extends AccountTest {

    @Test
    void shouldGetItem() {
        Account item = contentProvider.get();
        assertNotNull(item);
    }

    @Test
    void shouldGetItems() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        assertNotNull(items);
        assertEquals(numberOfItemsToLoad, items.size());
    }
}