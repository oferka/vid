package org.ok.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.ok.user.data.content.provider.ContentProvider;
import org.ok.user.data.content.provider.ContentProviderConfiguration;
import org.ok.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserApplication.class)
public abstract class UserTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ContentProvider contentProvider;

    @Autowired
    protected ContentProviderConfiguration contentProviderConfiguration;

    protected long contentCountBefore;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = userRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = userRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }

    protected int getNumberOfItemsToLoad() {
        return contentProviderConfiguration.getNumberOfItems();
    }
}
