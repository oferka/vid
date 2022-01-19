package org.ok.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ok.account.AccountTest;
import org.ok.account.model.Account;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.ok.TestDataUtils.getNonExistingId;
import static org.ok.integration.Paths.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AccountTest {

    private MockMvc mvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldFindAll() throws Exception {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        MvcResult mvcResult = mvc.perform(get(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(items.get(0).getId().toString())))
                .andReturn();
        assertNotNull(mvcResult);
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldFindById() throws Exception {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        Long id = items.get(0).getId();
        MvcResult mvcResult = mvc.perform(get(format("/%s/{id}", ACCOUNT_PATH), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(id.toString())))
                .andReturn();
        assertNotNull(mvcResult);
        accountRepository.deleteAll(saved);
    }

    @Test
    public void shouldNotFindById() throws Exception {
        MvcResult mvcResult = mvc.perform(get(format("/%s/{id}", ACCOUNT_PATH), getNonExistingId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        assertNotNull(mvcResult);
    }

    @Test
    public void shouldFindRandom() throws Exception {
        MvcResult mvcResult = mvc.perform(get(format("/%s/%s", ACCOUNT_PATH, RANDOM_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        assertNotNull(id);
    }

    @Test
    public void shouldFindRandomId() throws Exception {
        MvcResult mvcResult = mvc.perform(get(format("/%s/%s", ACCOUNT_PATH, RANDOM_ID_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
    }

    @Test
    public void shouldSave() throws Exception {
        Account item = contentProvider.get();
        MvcResult mvcResult = mvc.perform(post(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isCreated())
                .andReturn();
        assertNotNull(mvcResult);
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        assertNotNull(id);
        accountRepository.deleteById(id.longValue());
    }

    @Test
    public void shouldDelete() throws Exception {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        MvcResult mvcResult = mvc.perform(delete(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(saved))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        assertNotNull(id);
    }

    @Test
    public void shouldNotDelete() throws Exception {
        Account item = contentProvider.get();
        Account toBeDeleted = createAccountWithNonExistingId(item);
        MvcResult mvcResult = mvc.perform(delete(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(toBeDeleted))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        assertNotNull(mvcResult);
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        Long id = saved.getId();
        MvcResult mvcResult = mvc.perform(delete(format("/%s/{id}", ACCOUNT_PATH), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
        boolean exists = accountRepository.existsById(id);
        assertFalse(exists);
    }

    @Test
    public void shouldNotDeleteById() throws Exception {
        MvcResult mvcResult = mvc.perform(delete(format("/%s/{id}", ACCOUNT_PATH), getNonExistingId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        assertNotNull(mvcResult);
    }

    @Test
    public void shouldUpdate() throws Exception {
        Account item = contentProvider.get();
        Account saved = accountRepository.save(item);
        Long id = saved.getId();
        MvcResult mvcResult = mvc.perform(put(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
        Optional<Account> updated = accountRepository.findById(id);
        assertTrue(updated.isPresent());
        accountRepository.deleteById(id);
    }

    @Test
    public void shouldNotUpdate() throws Exception {
        Account item = contentProvider.get();
        Account toBeUpdated = createAccountWithNonExistingId(item);
        MvcResult mvcResult = mvc.perform(put(format("/%s", ACCOUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(toBeUpdated))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isNotFound())
                .andReturn();
        assertNotNull(mvcResult);
    }

    @Test
    public void shouldCount() throws Exception {
        List<Account> items = contentProvider.get(getNumberOfItemsToLoad());
        Iterable<Account> saved = accountRepository.saveAll(items);
        MvcResult mvcResult = mvc.perform(get(format("/%s/%s", ACCOUNT_PATH, COUNT_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult);
        accountRepository.deleteAll(saved);
    }
}