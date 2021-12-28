package org.ok.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.ok.account.model.Account;
import org.ok.account.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.ok.account.controller.Paths.ACCOUNT_PATH;
import static org.ok.account.controller.Paths.RANDOM_PATH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path = ACCOUNT_PATH)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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

    @Operation(summary = "Delete an account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account successfully deleted by id"),
            @ApiResponse(responseCode = "400", description = "Failed to delete account by id", content = @Content) })
    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity<Void> deleteById(@Parameter(description = "The id of the account to be deleted") @PathVariable("id") @NotNull Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
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
}
