package org.ok.account.data.content.verifier;

import lombok.extern.slf4j.Slf4j;
import org.ok.account.model.Account;
import org.ok.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContentVerifier {

    private final AccountService accountService;

    public ContentVerifier(AccountService accountService) {
        this.accountService = accountService;
    }

    public Iterable<Account> findLoaded(Iterable<Account> accounts) {
        List<Account> result = new ArrayList<>();
        for(Account account : accounts) {
            if(accountService.exists(account)) {
                result.add(account);
            }
        }
        log.info("{} accounts found as loaded", result.size());
        return result;
    }

    public Iterable<Account> findNotLoaded(Iterable<Account> accounts) {
        List<Account> result = new ArrayList<>();
        for(Account account : accounts) {
            if(!accountService.exists(account)) {
                result.add(account);
            }
        }
        log.info("{} accounts found as unloaded", result.size());
        return result;
    }
}
