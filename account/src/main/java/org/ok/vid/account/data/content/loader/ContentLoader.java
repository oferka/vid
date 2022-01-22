package org.ok.vid.account.data.content.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.ok.vid.account.data.content.provider.ContentProvider;
import org.ok.vid.account.data.content.provider.ContentProviderConfiguration;
import org.ok.vid.account.data.content.verifier.ContentVerifier;
import org.ok.vid.account.model.Account;
import org.ok.vid.account.service.AccountService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class ContentLoader {

    private final ContentProvider contentProvider;
    private final ContentVerifier contentVerifier;
    private final AccountService accountService;
    private final ContentProviderConfiguration contentProviderConfiguration;

    public ContentLoader(ContentProvider contentProvider,
                         ContentVerifier contentVerifier,
                         AccountService accountService,
                         ContentProviderConfiguration contentProviderConfiguration) {
        this.contentProvider = contentProvider;
        this.contentVerifier = contentVerifier;
        this.accountService = accountService;
        this.contentProviderConfiguration = contentProviderConfiguration;
    }

    public @NotNull Iterable<Account> ensureContentLoaded() {
        List<Account> content = contentProvider.get(contentProviderConfiguration.getNumberOfItems());
        Iterable<Account> unloadedContent = contentVerifier.findNotLoaded(content);
        if(!IterableUtils.isEmpty(unloadedContent)) {
            Iterable<Account> saved = accountService.saveAll(unloadedContent);
            log.info("{} accounts saved", IterableUtils.size(saved));
        }
        log.info("{} accounts ensured loaded", content.size());
        return content;
    }
}
