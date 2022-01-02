package org.ok.account.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.model.Account;
import org.ok.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.account.TestDataUtils.getNonExistingId;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContentProvider contentProvider;

    private long contentCountBefore;

    private final int numberOfItemsToLoad = 18;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = accountRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = accountRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }

    @Test
    public void shouldFindAll() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> savedItems = accountRepository.saveAll(items);
        List<Account> foundItems = accountService.findAll();
        assertNotNull(foundItems);
        accountRepository.deleteAll(savedItems);
    }

    @Test
    public void shouldFindById() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Long id = items.get(0).getId();
        Optional<Account> found = accountService.findById(id);
        assertTrue(found.isPresent());
        assertEquals(id, found.get().getId());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindById() {
        Optional<Account> found = accountService.findById(getNonExistingId());
        assertTrue(found.isEmpty());
    }
}