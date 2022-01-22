package org.ok.vid.user.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.ok.vid.user.model.User;
import org.ok.vid.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public @NotNull List<User> findAll() {
        Iterable<User> items = userRepository.findAll();
        return StreamSupport
                .stream(items.spliterator(), false)
                .collect(Collectors.toList());
    }

    public @NotNull Optional<User> findById(@NotNull Long id) {
        return userRepository.findById(id);
    }

    public @NotNull List<User> findByGender(@NotNull String gender) {
        return userRepository.findByGender(gender);
    }

    public @NotNull List<User> findByTitle(@NotNull String title) {
        return userRepository.findByTitle(title);
    }

    public @NotNull List<User> findByFirstName(@NotNull String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public @NotNull List<User> findByLastName(@NotNull String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public @NotNull List<User> findByStreetNumber(int streetNumber) {
        return userRepository.findByStreetNumber(streetNumber);
    }

    public List<User> findByStreetName(String streetName) {
        return userRepository.findByStreetName(streetName);
    }

    public List<User> findByCity(String city) {
        return userRepository.findByCity(city);
    }

    public List<User> findByState(String state) {
        return userRepository.findByState(state);
    }

    public List<User> findByCountry(String country) {
        return userRepository.findByCountry(country);
    }

    public List<User> findByPostcode(String postcode) {
        return userRepository.findByPostcode(postcode);
    }

    public List<User> findByLatitude(double latitude) {
        return userRepository.findByLatitude(latitude);
    }

    public List<User> findByLongitude(double longitude) {
        return userRepository.findByLongitude(longitude);
    }

    public List<User> findByTimezoneOffset(String timezoneOffset) {
        return userRepository.findByTimezoneOffset(timezoneOffset);
    }

    public List<User> findByTimezoneDescription(String timezoneDescription) {
        return userRepository.findByTimezoneDescription(timezoneDescription);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByDateOfBirth(ZonedDateTime dateOfBirth) {
        return userRepository.findByDateOfBirth(dateOfBirth);
    }

    public List<User> findByDateOfRegistration(ZonedDateTime dateOfRegistration) {
        return userRepository.findByDateOfRegistration(dateOfRegistration);
    }

    public List<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public List<User> findByCell(String cell) {
        return userRepository.findByCell(cell);
    }

    public List<User> findByLargePicture(String largePicture) {
        return userRepository.findByLargePicture(largePicture);
    }

    public List<User> findByMediumPicture(String mediumPicture) {
        return userRepository.findByMediumPicture(mediumPicture);
    }

    public List<User> findByThumbnailPicture(String thumbnailPicture) {
        return userRepository.findByThumbnailPicture(thumbnailPicture);
    }

    public List<User> findByNationality(String nationality) {
        return userRepository.findByNationality(nationality);
    }

    public Optional<User> findRandom() {
        List<User> items = findAll();
        if(items.isEmpty()) {
            return Optional.empty();
        }
        User item = items.get(RandomUtils.nextInt(0, items.size()));
        return Optional.of(item);
    }

    public Optional<Long> findRandomId() {
        List<User> items = findAll();
        if(items.isEmpty()) {
            return Optional.empty();
        }
        User item = items.get(RandomUtils.nextInt(0, items.size()));
        return Optional.of(item.getId());
    }

    public @NotNull User save(@NotNull User user) {
        return userRepository.save(user);
    }

    public @NotNull Iterable<User> saveAll(@NotNull Iterable<User> users) {
        return userRepository.saveAll(users);
    }

    public Optional<User> update(@NotNull User user) {
        Optional<User> result = Optional.empty();
        if(existsById(user.getId())) {
            result = Optional.of(save(user));
        }
        return result;
    }

    public Optional<User> delete(@NotNull User user) {
        return deleteById(user.getId());
    }

    public Optional<User> deleteById(@NotNull Long id) {
        Optional<User> item = findById(id);
        item.ifPresent(user -> userRepository.deleteById(user.getId()));
        return item;
    }

    public long count() {
        return userRepository.count();
    }

    public boolean existsById(@NotNull Long id) {
        return userRepository.existsById(id);
    }

    public boolean exists(@NotNull User user) {
        return userRepository.existsByGenderAndTitleAndFirstNameAndLastNameAndStreetNumberAndStreetNameAndCityAndStateAndCountryAndPostcodeAndLatitudeAndLongitudeAndTimezoneOffsetAndTimezoneDescriptionAndEmailAndDateOfBirthAndDateOfRegistrationAndPhoneAndCellAndLargePictureAndMediumPictureAndThumbnailPictureAndNationality(
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
