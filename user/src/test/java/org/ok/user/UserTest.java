package org.ok.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.ok.user.data.content.provider.ContentProvider;
import org.ok.user.data.content.provider.ContentProviderConfiguration;
import org.ok.user.model.User;
import org.ok.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.ok.TestDataUtils.getNonExistingId;

@SpringBootTest(classes = UserApplication.class)
public abstract class UserTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ContentProvider contentProvider;

    @Autowired
    protected ContentProviderConfiguration contentProviderConfiguration;

    protected long contentCountBefore;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = userRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = userRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }

    protected int getNumberOfItemsToLoad() {
        return contentProviderConfiguration.getNumberOfItems();
    }

    protected @NotNull User createUserWithNonExistingId(@NotNull User user) {
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
                user.getNationality(),
                user.getAccountId()
        );
    }
}
