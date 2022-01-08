package org.ok.user.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.ok.model.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String gender;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String title;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String firstName;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String lastName;

    @Getter
    @Positive
    private int streetNumber;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String streetName;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String city;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String state;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String country;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String postcode;

    @Getter
    private double latitude;

    @Getter
    private double longitude;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String timezoneOffset;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String timezoneDescription;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    @Email
    private String email;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @Getter
    @NotNull
    @Past
    private ZonedDateTime dateOfBirth;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @Getter
    @NotNull
    @Past
    private ZonedDateTime dateOfRegistration;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String phone;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String cell;

    @Getter
    @NotNull
    @URL
    private String largePicture;

    @Getter
    @NotNull
    @URL
    private String mediumPicture;

    @Getter
    @NotNull
    @URL
    private String thumbnailPicture;

    @Getter
    @NotNull
    @Size(min = 2, max = 64)
    @NotBlank
    private String nationality;

    @Getter
    private Long accountId;

    public User(Long id,
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
                String nationality,
                Long accountId) {
        super(id);
        this.gender = gender;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezoneOffset = timezoneOffset;
        this.timezoneDescription = timezoneDescription;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
        this.phone = phone;
        this.cell = cell;
        this.largePicture = largePicture;
        this.mediumPicture = mediumPicture;
        this.thumbnailPicture = thumbnailPicture;
        this.nationality = nationality;
        this.accountId = accountId;
    }

    public User(String gender,
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
                String nationality,
                Long accountId) {
        this(null, gender, title, firstName, lastName, streetNumber, streetName, city, state, country, postcode, latitude, longitude, timezoneOffset, timezoneDescription, email, dateOfBirth, dateOfRegistration, phone, cell, largePicture, mediumPicture, thumbnailPicture, nationality, accountId);
    }
}
