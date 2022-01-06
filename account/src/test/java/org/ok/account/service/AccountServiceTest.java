package org.ok.account.service;

import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.ok.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.getNonExistingId;
import static org.ok.TestDataUtils.getNonExistingName;

class AccountServiceTest extends AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void shouldFindAll() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> savedItems = accountRepository.saveAll(items);
        List<Account> foundItems = accountService.findAll();
        assertNotNull(foundItems);
        accountRepository.deleteAll(savedItems);
    }

    @Test
    public void shouldFindById() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
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
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
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
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
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
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
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
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
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
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountService.saveAll(items);
        assertNotNull(saved);
        accountRepository.deleteAll(items);
    }

    @Test
    public void shouldUpdate() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = items.get(0);
        Optional<Account> updated = accountService.update(item);
        assertTrue(updated.isPresent());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotUpdate() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = createAccountWithNonExistingId(items.get(0));
        Optional<Account> updated = accountService.update(item);
        assertTrue(updated.isEmpty());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldDelete() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = items.get(0);
        Optional<Account> deleted = accountService.delete(item);
        assertTrue(deleted.isPresent());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotDelete() {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        Account item = createAccountWithNonExistingId(items.get(0));
        Optional<Account> deleted = accountService.delete(item);
        assertTrue(deleted.isEmpty());
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldDeleteById() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        Long id = saved.getId();
        Optional<Account> deleted = accountService.deleteById(id);
        assertTrue(deleted.isPresent());
        boolean exists = accountRepository.existsById(id);
        assertFalse(exists);
    }

    @Test
    public void shouldNotDeleteById() {
        Optional<Account> deleted = accountService.deleteById(getNonExistingId());
        assertTrue(deleted.isEmpty());
    }

    @Test
    void shouldCount() {
        long countBefore = accountRepository.count();
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        long countAfter = accountService.count();
        assertEquals(countBefore + getNumberOfItemsToLoad(), countAfter);
        accountRepository.deleteAll(saved);
    }

    private @NotNull Account createAccountWithNonExistingId(@NotNull Account account) {
        return new Account(
                getNonExistingId(),
                account.getSymbol(),
                account.getName(),
                account.getSector()
        );
    }
}