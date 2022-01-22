package org.ok.vid.user.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class UserLatitudeProvider {

    public double get() {
        return Double.parseDouble(new Faker().address().latitude());
    }
}
