package org.ok.user.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.*;

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

    @Test
    void shouldFindItemByGender() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByGender(item.getGender());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getGender(), foundItem.getGender());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByGender() {
        List<User> foundItems = userRepository.findByGender(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByTitle() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByTitle(item.getTitle());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getTitle(), foundItem.getTitle());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByTitle() {
        List<User> foundItems = userRepository.findByTitle(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByFirstName() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByFirstName(item.getFirstName());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getFirstName(), foundItem.getFirstName());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByFirstName() {
        List<User> foundItems = userRepository.findByFirstName(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByLastName() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByLastName(item.getLastName());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getLastName(), foundItem.getLastName());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByLastName() {
        List<User> foundItems = userRepository.findByLastName(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByStreetNumber() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByStreetNumber(item.getStreetNumber());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getStreetNumber(), foundItem.getStreetNumber());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByStreetNumber() {
        List<User> foundItems = userRepository.findByStreetNumber(getNonExistingInteger());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByStreetName() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByStreetName(item.getStreetName());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getStreetName(), foundItem.getStreetName());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByStreetName() {
        List<User> foundItems = userRepository.findByStreetName(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByCity() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByCity(item.getCity());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getCity(), foundItem.getCity());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByCity() {
        List<User> foundItems = userRepository.findByCity(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByState() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByState(item.getState());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getState(), foundItem.getState());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByState() {
        List<User> foundItems = userRepository.findByState(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByCountry() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByCountry(item.getCountry());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getCountry(), foundItem.getCountry());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByCountry() {
        List<User> foundItems = userRepository.findByCountry(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByPostcode() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByPostcode(item.getPostcode());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getPostcode(), foundItem.getPostcode());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByPostcode() {
        List<User> foundItems = userRepository.findByPostcode(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByLatitude() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByLatitude(item.getLatitude());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getLatitude(), foundItem.getLatitude());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByLatitude() {
        List<User> foundItems = userRepository.findByLatitude(getNonExistingDouble());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByLongitude() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByLongitude(item.getLongitude());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getLongitude(), foundItem.getLongitude());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByLongitude() {
        List<User> foundItems = userRepository.findByLongitude(getNonExistingDouble());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByTimezoneOffset() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByTimezoneOffset(item.getTimezoneOffset());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getTimezoneOffset(), foundItem.getTimezoneOffset());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByTimezoneOffset() {
        List<User> foundItems = userRepository.findByTimezoneOffset(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByTimezoneDescription() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByTimezoneDescription(item.getTimezoneDescription());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getTimezoneDescription(), foundItem.getTimezoneDescription());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByTimezoneDescription() {
        List<User> foundItems = userRepository.findByTimezoneDescription(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByEmail() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByEmail(item.getEmail());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getEmail(), foundItem.getEmail());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByEmail() {
        List<User> foundItems = userRepository.findByEmail(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByDateOfBirth() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByDateOfBirth(item.getDateOfBirth());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertNotNull(foundItem);
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemBDateOfBirth() {
        List<User> foundItems = userRepository.findByDateOfBirth(getNonExistingDate());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByDateOfRegistration() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByDateOfRegistration(item.getDateOfRegistration());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertNotNull(foundItem);
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemBDateOfRegistration() {
        List<User> foundItems = userRepository.findByDateOfRegistration(getNonExistingDate());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByPhone() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByPhone(item.getPhone());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getPhone(), foundItem.getPhone());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByPhone() {
        List<User> foundItems = userRepository.findByPhone(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByCell() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByCell(item.getCell());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getCell(), foundItem.getCell());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByCell() {
        List<User> foundItems = userRepository.findByCell(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByLargePicture() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByLargePicture(item.getLargePicture());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getLargePicture(), foundItem.getLargePicture());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByLargePicture() {
        List<User> foundItems = userRepository.findByLargePicture(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByMediumPicture() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByMediumPicture(item.getMediumPicture());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getMediumPicture(), foundItem.getMediumPicture());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByMediumPicture() {
        List<User> foundItems = userRepository.findByMediumPicture(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByThumbnailPicture() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByThumbnailPicture(item.getThumbnailPicture());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getThumbnailPicture(), foundItem.getThumbnailPicture());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByThumbnailPicture() {
        List<User> foundItems = userRepository.findByThumbnailPicture(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }

    @Test
    void shouldFindItemByNationality() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        List<User> foundItems = userRepository.findByNationality(item.getNationality());
        assertFalse(foundItems.isEmpty());
        User foundItem = foundItems.get(0);
        assertEquals(item.getNationality(), foundItem.getNationality());
        userRepository.delete(saved);
    }

    @Test
    void shouldNotFindItemByNationality() {
        List<User> foundItems = userRepository.findByNationality(getNonExistingName());
        assertTrue(foundItems.isEmpty());
    }
    @Test
    void shouldFindAllItems() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Iterable<User> found = userRepository.findAll();
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedById() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Iterable<User> found = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedByFirstName() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Iterable<User> found = userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsSortedByDateOfRegistration() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Iterable<User> found = userRepository.findAll(Sort.by(Sort.Direction.DESC, "dateOfRegistration"));
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsPaged() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Page<User> found = userRepository.findAll(PageRequest.of(0, 4));
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldFindAllItemsPagedAndSorted() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Page<User> found = userRepository.findAll(PageRequest.of(0, 4, Sort.by(Sort.Direction.ASC, "lastName")));
        assertNotNull(found);
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldExistById() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        boolean exists = userRepository.existsById(saved.getId());
        assertTrue(exists);
        userRepository.delete(saved);
    }

    @Test
    void shouldNotExistById() {
        boolean exists = userRepository.existsById(getNonExistingId());
        assertFalse(exists);
    }

    @Test
    void shouldCount() {
        long countBefore = userRepository.count();
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        long countAfter = userRepository.count();
        assertEquals(countAfter, countBefore + getNumberOfItemsToLoad());
        userRepository.deleteAll(saved);
    }

    @Test
    void shouldDeleteItem() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        userRepository.delete(item);
        boolean exists = userRepository.existsById(saved.getId());
        assertFalse(exists);
    }

    @Test
    void shouldDeleteById() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        userRepository.deleteById(item.getId());
        boolean exists = userRepository.existsById(saved.getId());
        assertFalse(exists);
    }

    @Test
    void shouldNotDeleteById() {
        EmptyResultDataAccessException exception = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> userRepository.deleteById(getNonExistingId()));
        Assertions.assertTrue(Objects.requireNonNull(exception.getMessage()).contains("entity with id"));
    }

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
