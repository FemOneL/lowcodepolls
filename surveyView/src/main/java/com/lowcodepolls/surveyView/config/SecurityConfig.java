package com.lowcodepolls.surveyView.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetails userDetailsService() {
        UserDetails user2 = User.withUsername("user2@epam.com")
                .password(passwordEncoder().encode("user2Pass"))
                .roles("USER")
                .build();
        return user2;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Вимкнення CSRF захисту
                .authorizeRequests(auth -> auth
                        .requestMatchers("/anonymous*").anonymous() // Доступ до /anonymous* лише для анонімних користувачів
                        .requestMatchers("/login*").permitAll() // Доступ до /login* для всіх користувачів
                        .requestMatchers("/register*").permitAll() // Доступ до /login* для всіх користувачів
                        .anyRequest().authenticated() // Всі інші запити потребують аутентифікації
                )
                .formLogin(form -> form
                        .loginPage("/login") // Сторінка входу
                        .loginProcessingUrl("/login/perform_login") // URL для обробки входу
                        .defaultSuccessUrl("/login/homepage", true) // Сторінка за замовчуванням після успішного входу
                        .failureUrl("/login.html?error=true") // Сторінка в разі помилки входу
                )
                .logout(logout -> logout
                        .logoutUrl("/login/perform_logout") // URL для обробки виходу
                        .deleteCookies("JSESSIONID") // Видалення кукі після виходу
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}