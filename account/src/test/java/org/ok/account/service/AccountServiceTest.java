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
import static org.ok.account.TestDataUtils.getNonExistingName;

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

    @Test
    public void shouldFindBySymbol() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        String symbol = items.get(0).getSymbol();
        List<Account> found = accountService.findBySymbol(symbol);
        assertFalse(found.isEmpty());
        assertEquals(symbol, found.get(0).getSymbol());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindBySymbol() {
        List<Account> found = accountService.findBySymbol(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByName() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        String name = items.get(0).getName();
        List<Account> found = accountService.findByName(name);
        assertFalse(found.isEmpty());
        assertEquals(name, found.get(0).getName());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByName() {
        List<Account> found = accountService.findByName(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindBySector() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        String sector = items.get(0).getSector();
        List<Account> found = accountService.findBySector(sector);
        assertFalse(found.isEmpty());
        assertEquals(sector, found.get(0).getSector());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindBySector() {
        List<Account> found = accountService.findBySector(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindRandom() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Optional<Account> found = accountService.findRandom();
        assertTrue(found.isPresent());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldSave() {
        Account item = contentProvider.get();
        Account saved = accountService.save(item);
        assertEquals(saved, item);
        accountRepository.delete(item);
    }

    @Test
    public void shouldSaveAll() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountService.saveAll(items);
        assertNotNull(saved);
        accountRepository.deleteAll(items);
    }

    @Test
    public void shouldUpdate() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = items.get(0);
        Optional<Account> updated = accountService.update(item);
        assertTrue(updated.isPresent());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotUpdate() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = new Account(getNonExistingId(), items.get(0).getSymbol(), items.get(0).getName(), items.get(0).getSector());
        Optional<Account> updated = accountService.update(item);
        assertTrue(updated.isEmpty());
        accountRepository.deleteAll(saved);
    }

//    @Test
//    public void shouldDeleteById() {
//        Account item = sampleAccountProvider.getItem();
//        Account saved = accountElasticsearchRepository.save(item);
//        String id = saved.getId();
//        accountService.deleteById(id);
//        boolean exists = accountElasticsearchRepository.existsById(id);
//        assertFalse(exists);
//    }
//
//    @Test
//    public void shouldNotDeleteById() {
//        accountService.deleteById(getNonExistingId());
//    }
//
//    @Test
//    void shouldCount() {
//        long countBefore = accountElasticsearchRepository.count();
//        List<Account> items = sampleAccountProvider.getItems(numberOfItemsToLoad);
//        Iterable<Account> saved = accountElasticsearchRepository.saveAll(items);
//        long countAfter = accountService.count();
//        assertEquals(countBefore + numberOfItemsToLoad, countAfter);
//        accountElasticsearchRepository.deleteAll(saved);
//    }
}