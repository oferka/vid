package org.ok.account.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.account.TestDataUtils.getNonExistingId;
import static org.ok.account.TestDataUtils.getNonExistingName;

@SpringBootTest
class AccountRepositoryTest {

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
    void shouldSaveItem() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        assertEquals(item, saved);
        accountRepository.delete(saved);
    }

    @Test
    void shouldSaveItems() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        assertNotNull(saved);
        accountRepository.deleteAll(saved);
    }

    @Test
    void shouldFindItemById() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        Optional<Account> foundItemOptional = accountRepository.findById(item.getId());
        assertTrue(foundItemOptional.isPresent());
        Account foundItem = foundItemOptional.get();
        assertEquals(item.getId(), foundItem.getId());
        accountRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemById() {
        Optional<Account> foundItemOptional = accountRepository.findById(getNonExistingId());
        assertTrue(foundItemOptional.isEmpty());
    }

    @Test
    void shouldFindItemBySymbol() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        List<Account> foundItems = accountRepository.findBySymbol(item.getSymbol());
        assertFalse(foundItems.isEmpty());
        Account foundItem = foundItems.get(0);
        assertEquals(item.getSymbol(), foundItem.getSymbol());
        accountRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemBySymbol() {
        List<Account> foundItems = accountRepository.findBySymbol(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByName() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        List<Account> foundItems = accountRepository.findByName(item.getName());
        assertFalse(foundItems.isEmpty());
        Account foundItem = foundItems.get(0);
        assertEquals(item.getName(), foundItem.getName());
        accountRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByName() {
        List<Account> foundItems = accountRepository.findByName(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemBySector() {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        List<Account> foundItems = accountRepository.findBySector(item.getSector());
        assertFalse(foundItems.isEmpty());
        Account foundItem = foundItems.get(0);
        assertEquals(item.getSector(), foundItem.getSector());
        accountRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemBySector() {
        List<Account> foundItems = accountRepository.findByName(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindAllItems() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Iterable<Account> found = accountRepository.findAll();
        assertNotNull(found);
        accountRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedById() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        assertNotNull(found);
        accountRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedByName() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        assertNotNull(found);
        accountRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedBySector() {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
        Iterable<Account> saved = accountRepository.saveAll(items);
        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.DESC, "sector"));
        assertNotNull(found);
        accountRepository.deleteAll(saved);
    }

//    @Test
//    void shouldFindAllItemsPaged() {
//        List<Account> items = sampleAccountProvider.getItems(numberOfItemsToLoad);
//        Iterable<Account> saved = accountElasticsearchRepository.saveAll(items);
//        Page<Account> found = accountElasticsearchRepository.findAll(PageRequest.of(0, 4));
//        assertNotNull(found);
//        accountElasticsearchRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsPagedAndSorted() {
//        List<Account> items = sampleAccountProvider.getItems(numberOfItemsToLoad);
//        Iterable<Account> saved = accountElasticsearchRepository.saveAll(items);
//        Page<Account> found = accountElasticsearchRepository.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.ASC, "name")));
//        assertNotNull(found);
//        accountElasticsearchRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldExistById() {
//        Account item = sampleAccountProvider.getItem();
//        Account saved = accountElasticsearchRepository.save(item);
//        boolean exists = accountElasticsearchRepository.existsById(saved.getId());
//        assertTrue(exists);
//        accountElasticsearchRepository.delete(saved);
//    }
//
//    @Test
//    void shouldNotExistById() {
//        boolean exists = accountElasticsearchRepository.existsById(getNonExistingId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldCount() {
//        long countBefore = accountElasticsearchRepository.count();
//        List<Account> items = sampleAccountProvider.getItems(numberOfItemsToLoad);
//        Iterable<Account> saved = accountElasticsearchRepository.saveAll(items);
//        long countAfter = accountElasticsearchRepository.count();
//        assertEquals(countAfter, countBefore + numberOfItemsToLoad);
//        accountElasticsearchRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldDeleteItem() {
//        Account item = sampleAccountProvider.getItem();
//        Account saved = accountElasticsearchRepository.save(item);
//        accountElasticsearchRepository.delete(item);
//        boolean exists = accountElasticsearchRepository.existsById(saved.getId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldDeleteById() {
//        Account item = sampleAccountProvider.getItem();
//        Account saved = accountElasticsearchRepository.save(item);
//        accountElasticsearchRepository.deleteById(item.getId());
//        boolean exists = accountElasticsearchRepository.existsById(saved.getId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldNotDeleteById() {
//        accountElasticsearchRepository.deleteById(getNonExistingId());
//    }
//
//    @Test
//    void shouldDeleteItems() {
//        long countBefore = accountElasticsearchRepository.count();
//        List<Account> items = sampleAccountProvider.getItems(numberOfItemsToLoad);
//        Iterable<Account> saved = accountElasticsearchRepository.saveAll(items);
//        int numberOfItemsToDelete = 3;
//        accountElasticsearchRepository.deleteAll(items.subList(0, numberOfItemsToDelete));
//        long countAfter = accountElasticsearchRepository.count();
//        assertEquals((countBefore + numberOfItemsToLoad - numberOfItemsToDelete), countAfter);
//        accountElasticsearchRepository.deleteAll(saved);
//    }
}