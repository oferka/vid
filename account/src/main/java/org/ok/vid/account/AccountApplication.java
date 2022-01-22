package org.ok.vid.account;

import org.ok.vid.account.data.content.loader.ContentLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public CommandLineRunner ensureContentLoaded(ContentLoader contentLoader) {
        return args -> contentLoader.ensureContentLoaded();
    }
}
