package com.exam.jwy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        시큐리티 4.x버전부터는 disable()하지 않는 한 자동으로 활성화된다.
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/question/modify/**")).authenticated()
                .requestMatchers(
                        new AntPathRequestMatcher("/question/create**")).authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login")
                    .failureForwardUrl("/loginError")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
