package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

/**
 * DemoApplication 에 묶여있으면, 테스트 하기 번거로워질 수 있다.
 * 때문에 Config 으로 분리하는 것이 좋다
 */
@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // MEMO: 2022/10/23 리턴 타입을 람다형식으로 줄 수 있다
        return () -> Optional.ofNullable(UUID.randomUUID().toString());
    }

}
