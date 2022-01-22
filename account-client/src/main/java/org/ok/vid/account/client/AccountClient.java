package org.ok.vid.account.client;

import org.ok.vid.account.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.ok.vid.integration.Paths.*;
import static org.ok.vid.integration.ServiceNames.ACCOUNT_SERVICE_NAME;

@FeignClient(value = ACCOUNT_SERVICE_NAME, path = ACCOUNT_PATH)
public interface AccountClient {

    @GetMapping
    @NotNull ResponseEntity<List<Account>> findAll();

    @GetMapping(path = RANDOM_ID_PATH)
    @NotNull ResponseEntity<Long> findRandomId();

    @GetMapping(path = "{id}")
    @NotNull ResponseEntity<Account> findById(@PathVariable("id") @NotNull Long id);

    @GetMapping(path = RANDOM_PATH)
    @NotNull ResponseEntity<Account> findRandom();

    @PostMapping
    @NotNull ResponseEntity<Account> save(@RequestBody @NotNull Account account);

    @DeleteMapping()
    @NotNull ResponseEntity<Account> delete(@RequestBody @NotNull Account account);

    @DeleteMapping(path = "{id}")
    @NotNull ResponseEntity<Account> deleteById(@PathVariable("id") @NotNull Long id);

    @PutMapping
    @NotNull ResponseEntity<Account> update(@RequestBody @NotNull Account account);

    @GetMapping(path = COUNT_PATH)
    @NotNull ResponseEntity<Long> count();
}
