package org.ok.account.client;

import feign.Param;
import org.ok.account.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.ok.integration.Paths.*;
import static org.ok.integration.ServiceNames.ACCOUNT_SERVICE_NAME;

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
}
