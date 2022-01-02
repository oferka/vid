package org.ok.account.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ok.account.AccountApplication;
import org.ok.account.data.content.provider.ContentProvider;
import org.ok.account.model.Account;
import org.ok.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.ok.account.TestDataUtils.getNonExistingId;
import static org.ok.account.controller.Paths.ACCOUNT_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AccountApplication.class)
class AccountControllerTest {

    private MockMvc mvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContentProvider contentProvider;

    private long contentCountBefore;

    private final int numberOfItemsToLoad = 10;

    @BeforeEach
    void captureContentStatus() {
        contentCountBefore = accountRepository.count();
    }

    @AfterEach
    void verifyContentStatusNotChanged() {
        long contentCountAfter = accountRepository.count();
        assertEquals(contentCountBefore, contentCountAfter);
    }

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldFindAll() throws Exception {
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
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
        List<Account> items = contentProvider.get(numberOfItemsToLoad);
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
}