package org.ok.user;

import org.ok.account.client.AccountClient;
import org.ok.account.model.Account;
import org.ok.user.data.content.loader.ContentLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = { AccountClient.class })
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner ensureContentLoaded(ContentLoader contentLoader) {
        return args -> contentLoader.ensureContentLoaded();
    }

    @Bean
    public CommandLineRunner checkAccountClient(AccountClient accountClient) {
        ResponseEntity<List<Account>> response = accountClient.findAll();
        System.out.println(response);
        return args -> accountClient.findAll();
    }

    @Bean
    @LoadBalanced
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
