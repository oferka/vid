package org.ok.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AccountApplication.class)
public abstract class AccountTest {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected ContentProvider contentProvider;

    protected long contentCountBefore;

    protected final int numberOfItemsToLoad = 100;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = accountRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = accountRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }
}
