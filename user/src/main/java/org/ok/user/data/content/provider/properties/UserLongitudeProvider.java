package org.ok.user.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class UserLongitudeProvider {

    public double get() {
        return Double.parseDouble(new Faker().address().longitude());
    }
}
