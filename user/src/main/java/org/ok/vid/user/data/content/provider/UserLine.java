package org.ok.vid.user.data.content.provider;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLine {

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
}
