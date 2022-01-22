package org.ok.vid.account.data.content.provider;

import lombok.extern.slf4j.Slf4j;
import org.ok.vid.account.data.content.provider.properties.NameProvider;
import org.ok.vid.account.data.content.provider.properties.SectorProvider;
import org.ok.vid.account.data.content.provider.properties.SymbolProvider;
import org.ok.vid.account.model.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Profile("random-content-provider")
public class RandomContentProvider implements ContentProvider {

    private final SymbolProvider symbolProvider;
    private final NameProvider nameProvider;
    private final SectorProvider sectorProvider;

    public RandomContentProvider(SymbolProvider symbolProvider, NameProvider nameProvider, SectorProvider sectorProvider) {
        this.symbolProvider = symbolProvider;
        this.nameProvider = nameProvider;
        this.sectorProvider = sectorProvider;
    }

    @Override
    public @NotNull Account get() {
        return getAccount();
    }

    @Override
    public List<Account> get(int numberOfItems) {
        List<Account> result =  new ArrayList<>();
        for(int i=0; i<numberOfItems; i++) {
            result.add(getAccount());
        }
        log.info("{} accounts provided", result.size());
        return result;
    }

    private @NotNull Account getAccount() {
        return new Account(
                symbolProvider.get(),
                nameProvider.get(),
                sectorProvider.get()
        );
    }
}
