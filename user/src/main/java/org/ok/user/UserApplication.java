package org.ok.user;

import org.ok.user.data.content.loader.ContentLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner ensureContentLoaded(ContentLoader contentLoader) {
        return args -> contentLoader.ensureContentLoaded();
    }

}
