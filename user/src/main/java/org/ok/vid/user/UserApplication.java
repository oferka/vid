package org.ok.vid.user;

import org.ok.vid.account.client.AccountClient;
import org.ok.vid.user.data.content.loader.ContentLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
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
    @LoadBalanced
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }
}
