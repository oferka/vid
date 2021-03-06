package org.ok.vid.user.data.content.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ok.vid.user.data.content.provider.properties.*;
import org.ok.vid.user.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("random-content-provider")
public class RandomContentProvider implements ContentProvider {

    private final UserGenderProvider userGenderProvider;
    private final UserTitleProvider userTitleProvider;
    private final UserFirstNameProvider userFirstNameProvider;
    private final UserLastNameProvider userLastNameProvider;
    private final UserStreetNumberProvider userStreetNumberProvider;
    private final UserStreetNameProvider userStreetNameProvider;
    private final UserCityProvider userCityProvider;
    private final UserStateProvider userStateProvider;
    private final UserCountryProvider userCountryProvider;
    private final UserPostcodeProvider userPostcodeProvider;
    private final UserLatitudeProvider userLatitudeProvider;
    private final UserLongitudeProvider userLongitudeProvider;
    private final UserTimezoneOffsetProvider userTimezoneOffsetProvider;
    private final UserTimezoneDescriptionProvider userTimezoneDescriptionProvider;
    private final UserEmailProvider userEmailProvider;
    private final UserDateOfBirthProvider userDateOfBirthProvider;
    private final UserDateOfRegistrationProvider userDateOfRegistrationProvider;
    private final UserPhoneProvider userPhoneProvider;
    private final UserCellProvider userCellProvider;
    private final UserLargePictureProvider userLargePictureProvider;
    private final UserMediumPictureProvider userMediumPictureProvider;
    private final UserThumbnailPictureProvider userThumbnailPictureProvider;
    private final UserNationalityProvider userNationalityProvider;
    private final AccountIdProvider accountIdProvider;

    @Override
    public @NotNull User get() {
        return getUser();
    }

    @Override
    public List<User> get(int numberOfItems) {
        List<User> result =  new ArrayList<>();
        for(int i=0; i<numberOfItems; i++) {
            result.add(getUser());
        }
        log.info("{} accounts provided", result.size());
        return result;
    }

    private @NotNull User getUser() {
        return new User(
                userGenderProvider.get(),
                userTitleProvider.get(),
                userFirstNameProvider.get(),
                userLastNameProvider.get(),
                userStreetNumberProvider.get(),
                userStreetNameProvider.get(),
                userCityProvider.get(),
                userStateProvider.get(),
                userCountryProvider.get(),
                userPostcodeProvider.get(),
                userLatitudeProvider.get(),
                userLongitudeProvider.get(),
                userTimezoneOffsetProvider.get(),
                userTimezoneDescriptionProvider.get(),
                userEmailProvider.get(),
                userDateOfBirthProvider.get(),
                userDateOfRegistrationProvider.get(),
                userPhoneProvider.get(),
                userCellProvider.get(),
                userLargePictureProvider.get(),
                userMediumPictureProvider.get(),
                userThumbnailPictureProvider.get(),
                userNationalityProvider.get(),
                accountIdProvider.get()
        );
    }
}
