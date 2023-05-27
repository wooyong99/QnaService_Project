package com.exam.jwy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/question/modify/**")).authenticated()
                .requestMatchers(
                        new AntPathRequestMatcher("/question/create**")).authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login");
        return http.build();
    }
}
