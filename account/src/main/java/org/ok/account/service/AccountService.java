package org.ok.account.service;

import org.apache.commons.lang3.RandomUtils;
import org.ok.account.model.Account;
import org.ok.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public @NotNull List<Account> findAll() {
        Iterable<Account> items = accountRepository.findAll();
        return StreamSupport
                .stream(items.spliterator(), false)
                .collect(Collectors.toList());
    }

    public @NotNull Optional<Account> findById(@NotNull Long id) {
        return accountRepository.findById(id);
    }

    public @NotNull List<Account> findBySymbol(@NotNull String symbol) {
        return accountRepository.findBySymbol(symbol);
    }

    public @NotNull List<Account> findByName(@NotNull String name) {
        return accountRepository.findByName(name);
    }

    public @NotNull List<Account> findBySector(@NotNull String sector) {
        return accountRepository.findBySector(sector);
    }

    public Optional<Account> findRandom() {
        List<Account> items = findAll();
        if(items.isEmpty()) {
            return Optional.empty();
        }
        Account item = items.get(RandomUtils.nextInt(0, items.size()));
        return Optional.of(item);
    }

    public @NotNull Account save(@NotNull Account account) {
        return accountRepository.save(account);
    }

    public @NotNull Iterable<Account> saveAll(@NotNull Iterable<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }

    public Optional<Account> update(@NotNull Account account) {
        Optional<Account> result = Optional.empty();
        if(existsById(account.getId())) {
            result = Optional.of(save(account));
        }
        return result;
    }

    public Optional<Account> deleteById(@NotNull Long id) {
        Optional<Account> item = findById(id);
        item.ifPresent(account -> accountRepository.deleteById(account.getId()));
        return item;
    }

    public long count() {
        return accountRepository.count();
    }

    public boolean existsById(@NotNull Long id) {
        return accountRepository.existsById(id);
    }

    public boolean exists(@NotNull Account account) {
        Long id = account.getId();
        return ((id != null) && existsById(id));
    }
}
