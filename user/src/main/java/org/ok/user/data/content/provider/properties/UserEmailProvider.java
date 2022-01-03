package org.ok.user.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class UserEmailProvider {

    public @NotNull @Size(min = 2, max = 64) @NotBlank String get() {
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().firstName();
        String companyName = "something";
        return String.format("%s.%s@%s.com", firstName, lastName, companyName);
    }
}
