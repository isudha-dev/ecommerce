package dev.isudha.productcatalog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
        throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http
            .authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
            );
            // Form login handles the redirect to the login page from the
            // authorization server filter chain
//            .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
