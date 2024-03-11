package com.chainerist.blockchain.manager.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            try {
                return Optional.of("System");
            } catch (Exception e) {
            }
            return Optional.of("");
        };
    }
}
