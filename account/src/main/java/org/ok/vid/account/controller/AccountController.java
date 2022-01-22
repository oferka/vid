package org.ok.vid.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.ok.vid.account.model.Account;
import org.ok.vid.account.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.ok.vid.integration.Paths.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ACCOUNT_PATH)
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "Find all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts successfully found", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))}),
            @ApiResponse(responseCode = "400", description = "Failed to find accounts", content = @Content) }
    )
    public @NotNull ResponseEntity<List<Account>> findAll() {
        List<Account> items = accountService.findAll();
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Find an account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account successfully found by id", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "404", description = "Account with specified id was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find account by id", content = @Content) })
    @GetMapping(value = "{id}")
    public @NotNull ResponseEntity<Account> findById(@Parameter(description = "The id of the account to be found") @PathVariable("id") @NotNull Long id) {
        Optional<Account> item = accountService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a random account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random account successfully found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "404", description = "Random account was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random account", content = @Content) })
    @GetMapping(path = RANDOM_PATH)
    public @NotNull ResponseEntity<Account> findRandom() {
        Optional<Account> item = accountService.findRandom();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a random account ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random account ID successfully found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Random account ID was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random account ID", content = @Content) })
    @GetMapping(path = RANDOM_ID_PATH)
    public @NotNull ResponseEntity<Long> findRandomId() {
        Optional<Long> item = accountService.findRandomId();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to create an account", content = @Content) })
    @PostMapping
    public @NotNull ResponseEntity<Account> save(@Parameter(description = "Account to be saved") @RequestBody @Valid @NotNull Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = linkTo(AccountController.class).slash(account.getId()).toUri();
        httpHeaders.setLocation(location);
        Account saved = accountService.save(account);
        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Failed to delete account", content = @Content) })
    @DeleteMapping()
    public @NotNull ResponseEntity<Account> delete(@Parameter(description = "Account to be deleted") @RequestBody @NotNull @Valid Account account) {
        Optional<Account> deleted = accountService.delete(account);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account successfully deleted by id"),
            @ApiResponse(responseCode = "400", description = "Failed to delete account by id", content = @Content) })
    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity<Account> deleteById(@Parameter(description = "The id of the account to be deleted") @PathVariable("id") @NotNull Long id) {
        Optional<Account> deleted = accountService.deleteById(id);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to update account", content = @Content) })
    @PutMapping
    public @NotNull ResponseEntity<Account> update(@Parameter(description = "Account to be updated") @RequestBody @NotNull @Valid Account account) {
        Optional<Account> updated = accountService.update(account);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Return the number of existing accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts counted successfully", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to count accounts", content = @Content) })
    @GetMapping(path = COUNT_PATH)
    public @NotNull ResponseEntity<Long> count() {
        long count = accountService.count();
        return ResponseEntity.ok(count);
    }
}
