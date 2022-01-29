package org.ok.vid.metrics;

import io.micrometer.core.instrument.Tag;
import org.springframework.core.env.Environment;

import java.util.List;

public class TagUtils {

    public static Iterable<Tag> getCommonTags(Environment environment) {
        return List.of(
                Tag.of("application", environment.getProperty("spring.application.name")),
                Tag.of("instance", environment.getProperty("eureka.instance.instance-id"))
        );
    }
}
