package org.ok.user.service;

import org.junit.jupiter.api.Test;
import org.ok.user.UserTest;
import org.ok.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.getNonExistingId;

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

//    @Test
//    public void shouldFindBySymbol() {
//        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<User> saved = userService.saveAll(items);
//        String symbol = items.get(0).getSymbol();
//        List<User> found = userService.findBySymbol(symbol);
//        assertFalse(found.isEmpty());
//        assertEquals(symbol, found.get(0).getSymbol());
//        userRepository.deleteAll(saved);
//    }
//
//    @Test
//    public void shouldNotFindBySymbol() {
//        List<User> found = userService.findBySymbol(getNonExistingName());
//        assertTrue(found.isEmpty());
//    }
//
//    @Test
//    public void shouldFindByName() {
//        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<User> saved = userRepository.saveAll(items);
//        String name = items.get(0).getName();
//        List<User> found = userService.findByName(name);
//        assertFalse(found.isEmpty());
//        assertEquals(name, found.get(0).getName());
//        userRepository.deleteAll(saved);
//    }
//
//    @Test
//    public void shouldNotFindByName() {
//        List<User> found = userService.findByName(getNonExistingName());
//        assertTrue(found.isEmpty());
//    }
//
//    @Test
//    public void shouldFindBySector() {
//        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
//        Iterable<User> saved = userRepository.saveAll(items);
//        String sector = items.get(0).getSector();
//        List<User> found = userService.findBySector(sector);
//        assertFalse(found.isEmpty());
//        assertEquals(sector, found.get(0).getSector());
//        userRepository.deleteAll(saved);
//    }
//
//    @Test
//    public void shouldNotFindBySector() {
//        List<User> found = userService.findBySector(getNonExistingName());
//        assertTrue(found.isEmpty());
//    }

    @Test
    public void shouldFindRandom() {
        List<User> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<User> saved = userRepository.saveAll(items);
        Optional<User> found = userService.findRandom();
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

    private @NotNull User createUserWithNonExistingId(@NotNull User user) {
        return new User(
                getNonExistingId(),
                user.getGender(),
                user.getTitle(),
                user.getFirstName(),
                user.getLastName(),
                user.getStreetNumber(),
                user.getStreetName(),
                user.getCity(),
                user.getState(),
                user.getCountry(),
                user.getPostcode(),
                user.getLatitude(),
                user.getLongitude(),
                user.getTimezoneOffset(),
                user.getTimezoneDescription(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.getDateOfRegistration(),
                user.getPhone(),
                user.getCell(),
                user.getLargePicture(),
                user.getMediumPicture(),
                user.getThumbnailPicture(),
                user.getNationality()
        );
    }
}
