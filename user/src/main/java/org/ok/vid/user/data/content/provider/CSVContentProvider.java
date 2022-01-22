package org.ok.vid.user.data.content.provider;

import lombok.extern.slf4j.Slf4j;
import org.ok.vid.user.data.content.provider.properties.AccountIdProvider;
import org.ok.vid.user.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Profile({"csv-content-provider", "default"})
public class CSVContentProvider implements ContentProvider {

    private final CsvReader csvReader;
    private final AccountIdProvider accountIdProvider;

    public CSVContentProvider(CsvReader csvReader, AccountIdProvider accountIdProvider) {
        this.csvReader = csvReader;
        this.accountIdProvider = accountIdProvider;
    }

    @Override
    public @NotNull User get() {
        List<UserLine> lines = csvReader.read();
        return getAccount(lines.get(0));
    }

    @Override
    public List<User> get(int numberOfItems) {
        List<UserLine> lines = csvReader.read();
        List<User> result =  new ArrayList<>();
        for(int i=0; i<numberOfItems; i++) {
            result.add(getAccount(lines.get(i)));
        }
        log.info("{} users provided", result.size());
        return result;
    }

    private @NotNull User getAccount(@NotNull UserLine line) {
        return new User(
                line.getGender(),
                line.getTitle(),
                line.getFirstName(),
                line.getLastName(),
                line.getStreetNumber(),
                line.getStreetName(),
                line.getCity(),
                line.getState(),
                line.getCountry(),
                line.getPostcode(),
                line.getLatitude(),
                line.getLongitude(),
                line.getTimezoneOffset(),
                line.getTimezoneDescription(),
                line.getEmail(),
                line.getDateOfBirth(),
                line.getDateOfRegistration(),
                line.getPhone(),
                line.getCell(),
                line.getLargePicture(),
                line.getMediumPicture(),
                line.getThumbnailPicture(),
                line.getNationality(),
                accountIdProvider.get()
        );
    }
}
