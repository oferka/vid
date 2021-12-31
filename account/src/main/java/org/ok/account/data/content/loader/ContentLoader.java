package org.ok.account.data.content.loader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.data.content.verifier.ContentVerifier;
import org.ok.account.model.Account;
import org.ok.account.service.AccountService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class ContentLoader {

    private final ContentProvider contentProvider;
    private final ContentVerifier contentVerifier;
    private final AccountService accountService;

    public ContentLoader(ContentProvider contentProvider, ContentVerifier contentVerifier, AccountService accountService) {
        this.contentProvider = contentProvider;
        this.contentVerifier = contentVerifier;
        this.accountService = accountService;
    }

    public @NotNull Iterable<Account> ensureContentLoaded() {
        List<Account> content = contentProvider.get(5);
        Iterable<Account> unloadedContent = contentVerifier.findNotLoaded(content);
        if(!IterableUtils.isEmpty(unloadedContent)) {
            Iterable<Account> saved = accountService.saveAll(unloadedContent);
            log.info("{} accounts saved", IterableUtils.size(saved));
        }
        log.info("{} accounts ensured loaded", content.size());
        return content;
    }
}
