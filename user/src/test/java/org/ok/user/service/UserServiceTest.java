package org.ok.user.service;

import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.*;

@ActiveProfiles({ "csv-content-provider", "local-account-id-provider" })
public class UserServiceTest extends UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldFindAll() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> savedItems = userService.saveAll(items);
        List<User> foundItems = userService.findAll();
        assertNotNull(foundItems);
        userRepository.deleteAll(savedItems);
    }

    @Test
    public void shouldFindById() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userService.saveAll(items);
        Long id = items.get(0).getId();
        Optional<User> found = userService.findById(id);
        assertTrue(found.isPresent());
        assertEquals(id, found.get().getId());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindById() {
        Optional<User> found = userService.findById(getNonExistingId());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByGender() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String gender = items.get(0).getGender();
        List<User> found = userService.findByGender(gender);
        assertFalse(found.isEmpty());
        assertEquals(gender, found.get(0).getGender());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByGender() {
        List<User> found = userService.findByGender(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByTitle() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String title = items.get(0).getTitle();
        List<User> found = userService.findByTitle(title);
        assertFalse(found.isEmpty());
        assertEquals(title, found.get(0).getTitle());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByTitle() {
        List<User> found = userService.findByTitle(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByFirstName() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String firstName = items.get(0).getFirstName();
        List<User> found = userService.findByFirstName(firstName);
        assertFalse(found.isEmpty());
        assertEquals(firstName, found.get(0).getFirstName());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByFirstName() {
        List<User> found = userService.findByFirstName(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByLastName() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String lastName = items.get(0).getLastName();
        List<User> found = userService.findByLastName(lastName);
        assertFalse(found.isEmpty());
        assertEquals(lastName, found.get(0).getLastName());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByLastName() {
        List<User> found = userService.findByLastName(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByStreetNumber() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        int streetNumber = items.get(0).getStreetNumber();
        List<User> found = userService.findByStreetNumber(streetNumber);
        assertFalse(found.isEmpty());
        assertEquals(streetNumber, found.get(0).getStreetNumber());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByStreetNumber() {
        List<User> found = userService.findByStreetNumber(getNonExistingInteger());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByStreetName() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String streetName = items.get(0).getStreetName();
        List<User> found = userService.findByStreetName(streetName);
        assertFalse(found.isEmpty());
        assertEquals(streetName, found.get(0).getStreetName());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByStreetName() {
        List<User> found = userService.findByStreetName(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByCity() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String city = items.get(0).getCity();
        List<User> found = userService.findByCity(city);
        assertFalse(found.isEmpty());
        assertEquals(city, found.get(0).getCity());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByCity() {
        List<User> found = userService.findByCity(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByState() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String state = items.get(0).getState();
        List<User> found = userService.findByState(state);
        assertFalse(found.isEmpty());
        assertEquals(state, found.get(0).getState());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByState() {
        List<User> found = userService.findByState(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByCountry() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String country = items.get(0).getCountry();
        List<User> found = userService.findByCountry(country);
        assertFalse(found.isEmpty());
        assertEquals(country, found.get(0).getCountry());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByCountry() {
        List<User> found = userService.findByCountry(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByPostcode() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String postcode = items.get(0).getPostcode();
        List<User> found = userService.findByPostcode(postcode);
        assertFalse(found.isEmpty());
        assertEquals(postcode, found.get(0).getPostcode());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByPostcode() {
        List<User> found = userService.findByPostcode(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByLatitude() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        double latitude = items.get(0).getLatitude();
        List<User> found = userService.findByLatitude(latitude);
        assertFalse(found.isEmpty());
        assertEquals(latitude, found.get(0).getLatitude());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByLatitude() {
        List<User> found = userService.findByLatitude(getNonExistingDouble());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByLongitude() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        double longitude = items.get(0).getLongitude();
        List<User> found = userService.findByLongitude(longitude);
        assertFalse(found.isEmpty());
        assertEquals(longitude, found.get(0).getLongitude());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByLongitude() {
        List<User> found = userService.findByLongitude(getNonExistingDouble());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByTimezoneOffset() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String timezoneOffset = items.get(0).getTimezoneOffset();
        List<User> found = userService.findByTimezoneOffset(timezoneOffset);
        assertFalse(found.isEmpty());
        assertEquals(timezoneOffset, found.get(0).getTimezoneOffset());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByTimezoneOffset() {
        List<User> found = userService.findByTimezoneOffset(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByTimezoneDescription() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String timezoneDescription = items.get(0).getTimezoneDescription();
        List<User> found = userService.findByTimezoneDescription(timezoneDescription);
        assertFalse(found.isEmpty());
        assertEquals(timezoneDescription, found.get(0).getTimezoneDescription());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByTimezoneDescription() {
        List<User> found = userService.findByTimezoneDescription(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByEmail() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String email = items.get(0).getEmail();
        List<User> found = userService.findByEmail(email);
        assertFalse(found.isEmpty());
        assertEquals(email, found.get(0).getEmail());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByEmail() {
        List<User> found = userService.findByEmail(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByDateOfBirth() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        ZonedDateTime dateOfBirth = items.get(0).getDateOfBirth();
        List<User> found = userService.findByDateOfBirth(dateOfBirth);
        assertFalse(found.isEmpty());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByDateOfBirth() {
        List<User> found = userService.findByDateOfBirth(getNonExistingDate());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByDateOfRegistration() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        ZonedDateTime dateOfRegistration = items.get(0).getDateOfRegistration();
        List<User> found = userService.findByDateOfRegistration(dateOfRegistration);
        assertFalse(found.isEmpty());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByDateOfRegistration() {
        List<User> found = userService.findByDateOfRegistration(getNonExistingDate());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByPhone() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String phone = items.get(0).getPhone();
        List<User> found = userService.findByPhone(phone);
        assertFalse(found.isEmpty());
        assertEquals(phone, found.get(0).getPhone());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByPhone() {
        List<User> found = userService.findByPhone(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByCell() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String cell = items.get(0).getCell();
        List<User> found = userService.findByCell(cell);
        assertFalse(found.isEmpty());
        assertEquals(cell, found.get(0).getCell());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByCell() {
        List<User> found = userService.findByCell(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByLargePicture() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String largePicture = items.get(0).getLargePicture();
        List<User> found = userService.findByLargePicture(largePicture);
        assertFalse(found.isEmpty());
        assertEquals(largePicture, found.get(0).getLargePicture());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByLargePicture() {
        List<User> found = userService.findByLargePicture(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByMediumPicture() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String mediumPicture = items.get(0).getMediumPicture();
        List<User> found = userService.findByMediumPicture(mediumPicture);
        assertFalse(found.isEmpty());
        assertEquals(mediumPicture, found.get(0).getMediumPicture());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByMediumPicture() {
        List<User> found = userService.findByMediumPicture(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByThumbnailPicture() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String thumbnailPicture = items.get(0).getThumbnailPicture();
        List<User> found = userService.findByThumbnailPicture(thumbnailPicture);
        assertFalse(found.isEmpty());
        assertEquals(thumbnailPicture, found.get(0).getThumbnailPicture());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByThumbnailPicture() {
        List<User> found = userService.findByThumbnailPicture(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindByNationality() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        String nationality = items.get(0).getNationality();
        List<User> found = userService.findByNationality(nationality);
        assertFalse(found.isEmpty());
        assertEquals(nationality, found.get(0).getNationality());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindByNationality() {
        List<User> found = userService.findByNationality(getNonExistingName());
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindRandom() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Optional<User> found = userService.findRandom();
        assertTrue(found.isPresent());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldFindRandomId() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Optional<Long> found = userService.findRandomId();
        assertTrue(found.isPresent());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldSave() {
        User item = contentProvider.get();
        User saved = userService.save(item);
        assertEquals(saved, item);
        userRepository.delete(item);
    }

    @Test
    public void shouldSaveAll() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userService.saveAll(items);
        assertNotNull(saved);
        userRepository.deleteAll(items);
    }

    @Test
    public void shouldUpdate() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        User item = items.get(0);
        Optional<User> updated = userService.update(item);
        assertTrue(updated.isPresent());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotUpdate() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        User item = createUserWithNonExistingId(items.get(0));
        Optional<User> updated = userService.update(item);
        assertTrue(updated.isEmpty());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldDelete() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        User item = items.get(0);
        Optional<User> deleted = userService.delete(item);
        assertTrue(deleted.isPresent());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotDelete() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        User item = createUserWithNonExistingId(items.get(0));
        Optional<User> deleted = userService.delete(item);
        assertTrue(deleted.isEmpty());
        userRepository.deleteAll(saved);
    }

    @Test
    public void shouldDeleteById() {
        User item = contentProvider.get();
        User saved = userRepository.save(item);
        Long id = saved.getId();
        Optional<User> deleted = userService.deleteById(id);
        assertTrue(deleted.isPresent());
        boolean exists = userRepository.existsById(id);
        assertFalse(exists);
    }

    @Test
    public void shouldNotDeleteById() {
        Optional<User> deleted = userService.deleteById(getNonExistingId());
        assertTrue(deleted.isEmpty());
    }

    @Test
    void shouldCount() {
        long countBefore = userRepository.count();
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        long countAfter = userService.count();
        assertEquals(countBefore + getNumberOfItemsToLoad(), countAfter);
        userRepository.deleteAll(saved);
    }
}
