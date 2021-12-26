package org.ok.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.ok.account.model.Account;
import org.ok.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.ok.account.controller.Paths.ACCOUNT_PATH;

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
}
