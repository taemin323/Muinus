package com.hexa.muinus.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 다른 설정 중략
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/users/login", "/oauth2/**").permitAll()  // 허용 URL 설정
                                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                )
                .csrf().disable()  // CSRF 보호 비활성화 (API 서버의 경우)
                .formLogin().disable()  // 폼 로그인 비활성화
                .httpBasic().disable();  // HTTP Basic 인증 비활성화

        return http.build();
    }
}