package org.ok.account.sample;

import lombok.extern.slf4j.Slf4j;
import org.ok.account.data.content.provider.NameProvider;
import org.ok.account.data.content.provider.SectorProvider;
import org.ok.account.data.content.provider.SymbolProvider;
import org.ok.account.model.Account;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SampleAccountProvider {

    private final SymbolProvider symbolProvider;
    private final NameProvider nameProvider;
    private final SectorProvider sectorProvider;

    public SampleAccountProvider(SymbolProvider symbolProvider, NameProvider nameProvider, SectorProvider sectorProvider) {
        this.symbolProvider = symbolProvider;
        this.nameProvider = nameProvider;
        this.sectorProvider = sectorProvider;
    }

    public @NotNull Account getItem() {
        return getItem(1);
    }

    public @NotNull List<Account> getItems(int numberOfItems) {
        List<Account> result = new ArrayList<>();
        for(int i=0; i<numberOfItems; i++) {
            result.add(getItem(i+1));
        }
        return result;
    }

    private @NotNull Account getItem(int itemNumber) {
        Account result = new Account(
                symbolProvider.get(),
                nameProvider.get(),
                sectorProvider.get()
        );
        log.info("Account {} created: {}", itemNumber, result);
        return result;
    }
}
