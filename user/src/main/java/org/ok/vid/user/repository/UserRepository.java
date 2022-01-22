package org.ok.vid.user.repository;

import org.ok.vid.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGender(String gender);

    List<User> findByTitle(String title);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByStreetNumber(int streetNumber);

    List<User> findByStreetName(String streetName);

    List<User> findByCity(String city);

    List<User> findByState(String state);

    List<User> findByCountry(String country);

    List<User> findByPostcode(String postcode);

    List<User> findByLatitude(double latitude);

    List<User> findByLongitude(double longitude);

    List<User> findByTimezoneOffset(String timezoneOffset);

    List<User> findByTimezoneDescription(String timezoneDescription);

    List<User> findByEmail(String email);

    List<User> findByDateOfBirth(ZonedDateTime dateOfBirth);

    List<User> findByDateOfRegistration(ZonedDateTime dateOfRegistration);

    List<User> findByPhone(String phone);

    List<User> findByCell(String cell);

    List<User> findByLargePicture(String largePicture);

    List<User> findByMediumPicture(String mediumPicture);

    List<User> findByThumbnailPicture(String thumbnailPicture);

    List<User> findByNationality(String nationality);

    Boolean existsByGenderAndTitleAndFirstNameAndLastNameAndStreetNumberAndStreetNameAndCityAndStateAndCountryAndPostcodeAndLatitudeAndLongitudeAndTimezoneOffsetAndTimezoneDescriptionAndEmailAndDateOfBirthAndDateOfRegistrationAndPhoneAndCellAndLargePictureAndMediumPictureAndThumbnailPictureAndNationality(
            String gender,
            String title,
            String firstName,
            String lastName,
            int streetNumber,
            String streetName,
            String city,
            String state,
            String country,
            String postcode,
            double latitude,
            double longitude,
            String timezoneOffset,
            String timezoneDescription,
            String email,
            ZonedDateTime dateOfBirth,
            ZonedDateTime dateOfRegistration,
            String phone,
            String cell,
            String largePicture,
            String mediumPicture,
            String thumbnailPicture,
            String nationality);
}
