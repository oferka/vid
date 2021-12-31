package org.ok.account.data.content.provider;

import lombok.extern.slf4j.Slf4j;
import org.ok.account.model.Account;
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

    public CSVContentProvider(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public List<Account> get(int numberOfItems) {
        List<AccountLine> lines = csvReader.read();
        List<Account> result =  new ArrayList<>();
        for(int i=0; i<numberOfItems; i++) {
            result.add(getAccount(lines.get(i)));
        }
        log.info("{} accounts provided", result.size());
        return result;
    }

    private @NotNull Account getAccount(@NotNull AccountLine line) {
        return new Account(
                line.getSymbol(),
                line.getName(),
                line.getSector()
        );
    }
}
