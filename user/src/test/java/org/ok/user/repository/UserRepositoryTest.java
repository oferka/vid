package org.ok.user.repository;

import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.getNonExistingId;

public class UserRepositoryTest extends UserTest {

    @Test
    void shouldSaveItem() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        assertEquals(item, saved);
        userRepository.delete(saved);
    }

    @Test
    void shouldSaveItems() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        assertNotNull(saved);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindItemById() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        Optional<User> foundItemOptional = userRepository.findById(item.getId());
        assertTrue(foundItemOptional.isPresent());
        User foundItem = foundItemOptional.get();
        assertEquals(item.getId(), foundItem.getId());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemById() {
        Optional<User> foundItemOptional = userRepository.findById(getNonExistingId());
        assertTrue(foundItemOptional.isEmpty());
    }

//    @Test
//    void shouldFindItemBySymbol() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        List<Account> foundItems = accountRepository.findBySymbol(item.getSymbol());
//        assertFalse(foundItems.isEmpty());
//        Account foundItem = foundItems.get(0);
//        assertEquals(item.getSymbol(), foundItem.getSymbol());
//        accountRepository.delete(saved);
//    }
//
//    @Test
//    void shouldNotFindItemBySymbol() {
//        List<Account> foundItems = accountRepository.findBySymbol(getNonExistingName());
//        assertTrue(foundItems.isEmpty());
//    }
//
//    @Test
//    void shouldFindItemByName() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        List<Account> foundItems = accountRepository.findByName(item.getName());
//        assertFalse(foundItems.isEmpty());
//        Account foundItem = foundItems.get(0);
//        assertEquals(item.getName(), foundItem.getName());
//        accountRepository.delete(saved);
//    }
//
//    @Test
//    void shouldNotFindItemByName() {
//        List<Account> foundItems = accountRepository.findByName(getNonExistingName());
//        assertTrue(foundItems.isEmpty());
//    }
//
//    @Test
//    void shouldFindItemBySector() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        List<Account> foundItems = accountRepository.findBySector(item.getSector());
//        assertFalse(foundItems.isEmpty());
//        Account foundItem = foundItems.get(0);
//        assertEquals(item.getSector(), foundItem.getSector());
//        accountRepository.delete(saved);
//    }
//
//    @Test
//    void shouldNotFindItemBySector() {
//        List<Account> foundItems = accountRepository.findByName(getNonExistingName());
//        assertTrue(foundItems.isEmpty());
//    }
//
//    @Test
//    void shouldFindAllItems() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Iterable<Account> found = accountRepository.findAll();
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsSortedById() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsSortedByName() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsSortedBySector() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Iterable<Account> found = accountRepository.findAll(Sort.by(Sort.Direction.DESC, "sector"));
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsPaged() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Page<Account> found = accountRepository.findAll(PageRequest.of(0, 4));
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldFindAllItemsPagedAndSorted() {
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        Page<Account> found = accountRepository.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.ASC, "name")));
//        assertNotNull(found);
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldExistById() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        boolean exists = accountRepository.existsById(saved.getId());
//        assertTrue(exists);
//        accountRepository.delete(saved);
//    }
//
//    @Test
//    void shouldNotExistById() {
//        boolean exists = accountRepository.existsById(getNonExistingId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldCount() {
//        long countBefore = accountRepository.count();
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        long countAfter = accountRepository.count();
//        assertEquals(countAfter, countBefore + getNumberOfItemsToLoad());
//        accountRepository.deleteAll(saved);
//    }
//
//    @Test
//    void shouldDeleteItem() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        accountRepository.delete(item);
//        boolean exists = accountRepository.existsById(saved.getId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldDeleteById() {
//        Account item = contentProvider.get();
//        Account saved = accountRepository.save(item);
//        accountRepository.deleteById(item.getId());
//        boolean exists = accountRepository.existsById(saved.getId());
//        assertFalse(exists);
//    }
//
//    @Test
//    void shouldNotDeleteById() {
//        EmptyResultDataAccessException exception = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> accountRepository.deleteById(getNonExistingId()));
//        Assertions.assertTrue(Objects.requireNonNull(exception.getMessage()).contains("entity with id"));
//    }
//
//    @Test
//    void shouldDeleteItems() {
//        long countBefore = accountRepository.count();
//        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<Account> saved = accountRepository.saveAll(items);
//        int numberOfItemsToDelete = 3;
//        accountRepository.deleteAll(items.subList(0, numberOfItemsToDelete));
//        long countAfter = accountRepository.count();
//        assertEquals((countBefore + getNumberOfItemsToLoad() - numberOfItemsToDelete), countAfter);
//        accountRepository.deleteAll(saved);
//    }
}
