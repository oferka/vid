package org.ok.vid;

import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class TestDataUtils {

    public static @NotNull Long getNonExistingId() {
        return RandomUtils.nextLong();
    }

    public static @NotNull String getNonExistingName() {
        return "No Such Name";
    }

    public static @NotNull int getNonExistingInteger() {
        return 13457452;
    }

    public static @NotNull double getNonExistingDouble() {
        return 134.57452D;
    }

    public static @NotNull ZonedDateTime getNonExistingDate() {
        return ZonedDateTime.now().plusSeconds(1234567L);
    }
}
