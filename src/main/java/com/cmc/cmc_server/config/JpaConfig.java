package com.cmc.cmc_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EnableJpaAuditing 어노테이션을 추가하기 위한 Config 파일 입니다.
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
