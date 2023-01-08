package com.june.sample.userservice.core.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class CommonConfig {
    /**
     * MessageSource 설정.
     */
    @Bean
    public MessageSource messageSource() {
        Locale.setDefault(Locale.KOREA);

        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasename("messages/messages");
        bundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return bundleMessageSource;
    }
}
