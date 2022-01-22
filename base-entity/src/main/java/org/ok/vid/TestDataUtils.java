package org.ok.vid;

import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class TestDataUtils {

    public static final String NONE_EXISTING_NAME = "No Such Name";
    public static final int NONE_EXISTING_INTEGER = 13457452;
    public static final double NONE_EXISTING_DOUBLE = 134.57452D;

    public static @NotNull Long getNonExistingId() {
        return RandomUtils.nextLong();
    }

    public static @NotNull ZonedDateTime getNonExistingDate() {
        return ZonedDateTime.now().plusSeconds(1234567L);
    }
}
