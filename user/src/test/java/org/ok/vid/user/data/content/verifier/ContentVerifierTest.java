package org.ok.vid.user.data.content.verifier;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.ok.vid.user.UserTest;
import org.ok.vid.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
public class ContentVerifierTest extends UserTest {

    @Autowired
    private ContentVerifier contentVerifier;

    @Test
    void shouldFindLoaded() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> loaded = contentVerifier.findLoaded(items);
        assertEquals(IterableUtils.toList(loaded), items);
    }

    @Test
    void shouldFindNotLoaded() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> loaded = contentVerifier.findNotLoaded(items);
        assertTrue(IterableUtils.toList(loaded).isEmpty());
    }
}
