package org.ok.vid.account.data.content.verifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ok.vid.account.model.Account;
import org.ok.vid.account.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentVerifier {

    private final AccountService accountService;

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
