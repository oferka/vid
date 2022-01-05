package org.ok.user.data.content.provider;

import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContentProviderTest extends UserTest {

    @Test
    void shouldGetItem() {
        User item = contentProvider.get();
        assertNotNull(item);
    }

    @Test
    void shouldGetItems() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        assertNotNull(items);
        assertEquals(getNumberOfItemsToLoad(), items.size());
    }
}