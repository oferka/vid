package org.ok.vid.account.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class NameProvider {

    public @NotNull @Size(min = 2, max = 64) @NotBlank String get() {
        return new Faker().company().name();
    }
}
