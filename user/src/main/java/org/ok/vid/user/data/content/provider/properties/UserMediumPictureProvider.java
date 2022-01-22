package org.ok.vid.user.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserMediumPictureProvider {

    public @NotNull @URL String get() {
        return new Faker().avatar().image();
    }
}
