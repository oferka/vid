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
    @Timed(value = "UserController.findAll.timer", description = "Timer for user findAll endpoint", extraTags = {"tag1.key", "tag1.value", "tag2.key", "tag2.value"}, percentiles = { 0.05,0.50, 0.95})
    @Counted(value = "UserController.findAll.counter", description = "Counter for user findAll endpoint", extraTags = {"tag1.key", "tag1.value", "tag2.key", "tag2.value"})
    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
            @ApiResponse(responseCode = "400", description = "Failed to find users", content = @Content) }
    )
    public @NotNull ResponseEntity<List<User>> findAll() {
        List<User> items = userService.findAll();
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Find a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully found by id", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User with specified id was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find user by id", content = @Content) })
    @GetMapping(value = "{id}")
    public @NotNull ResponseEntity<User> findById(@Parameter(description = "The id of the user to be found") @PathVariable("id") @NotNull Long id) {
        Optional<User> item = userService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a random user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random user successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Random user was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random user", content = @Content) })
    @GetMapping(path = RANDOM_PATH)
    public @NotNull ResponseEntity<User> findRandom() {
        Optional<User> item = userService.findRandom();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a random user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random user ID successfully found", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "404", description = "Random user ID was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to find a random user ID", content = @Content) })
    @GetMapping(path = RANDOM_ID_PATH)
    public @NotNull ResponseEntity<Long> findRandomId() {
        Optional<Long> item = userService.findRandomId();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to create an user", content = @Content) })
    @PostMapping
    public @NotNull ResponseEntity<User> save(@Parameter(description = "User to be saved") @RequestBody @Valid @NotNull User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = linkTo(UserController.class).slash(user.getId()).toUri();
        httpHeaders.setLocation(location);
        User saved = userService.save(user);
        return new ResponseEntity<>(saved, httpHeaders, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Failed to delete user", content = @Content) })
    @DeleteMapping()
    public @NotNull ResponseEntity<User> delete(@Parameter(description = "User to be deleted") @RequestBody @NotNull @Valid User user) {
        Optional<User> deleted = userService.delete(user);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted by id"),
            @ApiResponse(responseCode = "400", description = "Failed to delete user by id", content = @Content) })
    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity<User> deleteById(@Parameter(description = "The id of the user to be deleted") @PathVariable("id") @NotNull Long id) {
        Optional<User> deleted = userService.deleteById(id);
        return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to update user", content = @Content) })
    @PutMapping
    public @NotNull ResponseEntity<User> update(@Parameter(description = "User to be updated") @RequestBody @NotNull @Valid User user) {
        Optional<User> updated = userService.update(user);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Return the number of existing users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users counted successfully", content = { @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to count users", content = @Content) })
    @GetMapping(path = COUNT_PATH)
    public @NotNull ResponseEntity<Long> count() {
        long count = userService.count();
        return ResponseEntity.ok(count);
    }
}
