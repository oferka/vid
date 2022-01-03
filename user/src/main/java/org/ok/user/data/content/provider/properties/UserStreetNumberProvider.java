package org.ok.user.data.content.provider.properties;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class UserStreetNumberProvider {

    public int get() {
        return Integer.parseInt(new Faker().address().streetAddressNumber());
    }
}
