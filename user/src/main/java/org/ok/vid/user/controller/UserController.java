package org.ok.vid.user.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.ok.vid.user.model.User;
import org.ok.vid.user.service.UserService;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = USER_PATH)
public class UserController {

    private final UserService userService;

    @GetMapping
    @Timed(value = "UserController.findAll.timer", description = "Timer for user findAll endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.findAll.counter", description = "Counter for user findAll endpoint")
    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
            @ApiResponse(responseCode = "400", description = "Failed to find users", content = @Content) }
    )
    public @NotNull ResponseEntity<List<User>> findAll() {
        List<User> items = userService.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping(value = "{id}")
    @Timed(value = "UserController.findById.timer", description = "Timer for user findById endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.findById.counter", description = "Counter for user findById endpoint")
    @Operation(summary = "Find a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully found by id", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User with specified id was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find user by id", content = @Content) })
    public @NotNull ResponseEntity<User> findById(@Parameter(description = "The id of the user to be found") @PathVariable("id") @NotNull Long id) {
        Optional<User> item = userService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = RANDOM_PATH)
    @Timed(value = "UserController.findRandom.timer", description = "Timer for user findRandom endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.findRandom.counter", description = "Counter for user findRandom endpoint")
    @Operation(summary = "Find a random user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random user successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Random user was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random user", content = @Content) })
    public @NotNull ResponseEntity<User> findRandom() {
        Optional<User> item = userService.findRandom();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = RANDOM_ID_PATH)
    @Timed(value = "UserController.findRandomId.timer", description = "Timer for user findRandomId endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.findRandomId.counter", description = "Counter for user findRandomId endpoint")
    @Operation(summary = "Find a random user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random user ID successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Random user ID was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random user ID", content = @Content) })
    public @NotNull ResponseEntity<Long> findRandomId() {
        Optional<Long> item = userService.findRandomId();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Timed(value = "UserController.save.timer", description = "Timer for user save endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.save.counter", description = "Counter for user save endpoint")
    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to create an user", content = @Content) })
    public @NotNull ResponseEntity<User> save(@Parameter(description = "User to be saved") @RequestBody @Valid @NotNull User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = linkTo(UserController.class).slash(user.getId()).toUri();
        httpHeaders.setLocation(location);
        User saved = userService.save(user);
        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping()
    @Timed(value = "UserController.delete.timer", description = "Timer for user delete endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.delete.counter", description = "Counter for user delete endpoint")
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Failed to delete user", content = @Content) })
    public @NotNull ResponseEntity<User> delete(@Parameter(description = "User to be deleted") @RequestBody @NotNull @Valid User user) {
        Optional<User> deleted = userService.delete(user);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Timed(value = "UserController.deleteById.timer", description = "Timer for user deleteById endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.deleteById.counter", description = "Counter for user deleteById endpoint")
    @Operation(summary = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted by id"),
            @ApiResponse(responseCode = "400", description = "Failed to delete user by id", content = @Content) })
    public @NotNull ResponseEntity<User> deleteById(@Parameter(description = "The id of the user to be deleted") @PathVariable("id") @NotNull Long id) {
        Optional<User> deleted = userService.deleteById(id);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @Timed(value = "UserController.update.timer", description = "Timer for user deleteById endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.update.counter", description = "Counter for user update endpoint")
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to update user", content = @Content) })
    public @NotNull ResponseEntity<User> update(@Parameter(description = "User to be updated") @RequestBody @NotNull @Valid User user) {
        Optional<User> updated = userService.update(user);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = COUNT_PATH)
    @Timed(value = "UserController.count.timer", description = "Timer for user count endpoint", percentiles = { 0.01, 0.05,0.50, 0.95, 0.99})
    @Counted(value = "UserController.count.counter", description = "Counter for user count endpoint")
    @Operation(summary = "Return the number of existing users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users counted successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to count users", content = @Content) })
    public @NotNull ResponseEntity<Long> count() {
        long count = userService.count();
        return ResponseEntity.ok(count);
    }
}
