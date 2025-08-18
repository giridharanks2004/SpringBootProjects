package dev.giridharanks.springproject.uitils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordENC() throws Exception{
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http, Jwtfilter jwtfilter) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((auth)->auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated())
            .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
