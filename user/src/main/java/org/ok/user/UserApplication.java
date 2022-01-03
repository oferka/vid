package org.ok.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner ensureContentLoaded(ContentLoader contentLoader) {
//        return args -> contentLoader.ensureContentLoaded();
//    }
}
