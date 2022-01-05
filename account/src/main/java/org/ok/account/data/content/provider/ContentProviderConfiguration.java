package org.ok.account.data.content.provider;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Positive;

@Configuration
@PropertySource("classpath:content-provider-configuration.properties")
@ConfigurationProperties(prefix = "content.provider")
@Data
public class ContentProviderConfiguration {

    @Positive
    private Integer numberOfItems = 100;
}
