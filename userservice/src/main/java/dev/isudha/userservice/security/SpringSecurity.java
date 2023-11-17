package dev.isudha.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        //        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        return http.build();
    }
    // SecurityFilterChain object handles what all api endpoint should be authenticated
    // and which all shouldn't

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
