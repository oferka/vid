package org.ok.user.data.content.provider.properties;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class UserGenderProvider {

    public @NotNull @Size(min = 2, max = 64) @NotBlank String get() {
        return RandomUtils.nextBoolean()? "male" : "female";
    }
}
