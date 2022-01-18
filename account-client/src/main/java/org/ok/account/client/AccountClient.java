package org.ok.account.client;

import org.ok.account.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(value = "accountClient", url = "http://localhost:65198/api/rest/account")
public interface AccountClient {

    @GetMapping
    @NotNull ResponseEntity<List<Account>> findAll();
}
