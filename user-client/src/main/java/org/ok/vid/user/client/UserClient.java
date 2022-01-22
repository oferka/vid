package org.ok.vid.user.client;

import org.ok.vid.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.ok.vid.integration.Paths.*;
import static org.ok.vid.integration.ServiceNames.USER_SERVICE_NAME;

@FeignClient(value = USER_SERVICE_NAME, path = USER_PATH)
public interface UserClient {

    @GetMapping
    @NotNull ResponseEntity<List<User>> findAll();

    @GetMapping(path = RANDOM_ID_PATH)
    @NotNull ResponseEntity<User> findRandomId();

    @GetMapping(path = "{id}")
    @NotNull ResponseEntity<User> findById(@PathVariable("id") @NotNull Long id);

    @GetMapping(path = RANDOM_PATH)
    @NotNull ResponseEntity<User> findRandom();

    @PostMapping
    @NotNull ResponseEntity<User> save(@RequestBody @NotNull User account);

    @DeleteMapping()
    @NotNull ResponseEntity<User> delete(@RequestBody @NotNull User account);

    @DeleteMapping(path = "{id}")
    @NotNull ResponseEntity<User> deleteById(@PathVariable("id") @NotNull Long id);

    @PutMapping
    @NotNull ResponseEntity<User> update(@RequestBody @NotNull User account);

    @GetMapping(path = COUNT_PATH)
    @NotNull ResponseEntity<Long> count();
}
