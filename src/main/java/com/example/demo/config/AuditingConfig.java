package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class ListenerConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // MEMO: 2022/10/23 리턴 타입을 람다형식으로 줄 수 있다
        return () -> Optional.ofNullable(UUID.randomUUID().toString());
    }

}
