package org.ok.account;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.data.content.provider.ContentProviderConfiguration;
import org.ok.account.model.Account;
import org.ok.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ok.TestDataUtils.getNonExistingId;

@SpringBootTest(classes = AccountApplication.class)
public abstract class AccountTest {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected ContentProvider contentProvider;

    @Autowired
    protected ContentProviderConfiguration contentProviderConfiguration;

    protected long contentCountBefore;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = accountRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = accountRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }

    protected int getNumberOfItemsToLoad() {
        return contentProviderConfiguration.getNumberOfItems();
    }

    protected @NotNull Account createAccountWithNonExistingId(@NotNull Account account) {
        return new Account(
                getNonExistingId(),
                account.getSymbol(),
                account.getName(),
                account.getSector()
        );
    }
}
