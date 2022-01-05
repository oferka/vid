package org.ok;

import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.NotNull;

public class TestDataUtils {

    public static @NotNull Long getNonExistingId() {
        return RandomUtils.nextLong();
    }

    public static @NotNull String getNonExistingName() {
        return "No Such Name";
    }
}
