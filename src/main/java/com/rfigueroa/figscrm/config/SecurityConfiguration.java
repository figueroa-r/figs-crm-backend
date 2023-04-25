package com.rfigueroa.figscrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // create security config
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails rafael = User.builder()
                .username("Rafael")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(rafael);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((configurer) -> configurer
                            .antMatchers("/api/v2/customers").hasRole("ADMIN")
                            .anyRequest().authenticated()
        );

        // use HTTP basic authentication - uses cookies
        http.httpBasic();

        // disable Cross Site Request Forgery (CSRF)
        http.csrf().disable();

//        http
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();

        return http.build();
    }
}
