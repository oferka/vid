package org.ok.vid.account;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ok.vid.account.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import static org.ok.vid.integration.ServiceNames.ACCOUNT_SERVICE_NAME;
import static org.ok.vid.metrics.TagUtils.getCommonTags;

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
        return args -> registry.config().commonTags(getCommonTags(environment));
    }

    @Bean
    CommandLineRunner addEntityCountGauge(AccountRepository repository) {
        return args -> Gauge.builder(ACCOUNT_SERVICE_NAME + ".entity.count", repository::count).register(registry);
    }
}
