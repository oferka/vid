package org.ok.vid.account;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
@RequiredArgsConstructor
public class MetricsConfiguration {

    private final MeterRegistry registry;
    private final Environment environment;

    @Bean
    CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    @Bean
    CommandLineRunner configureMeterRegistry() {
        return args -> registry.config().commonTags(getCommonTags());
    }

    private Iterable<Tag> getCommonTags() {
        return List.of(
                Tag.of("spring.application.name", environment.getProperty("spring.application.name")),
                Tag.of("eureka.instance.instance-id", environment.getProperty("eureka.instance.instance-id"))
        );
    }
}
