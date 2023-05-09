package com.rfigueroa.figscrm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class FigsCrmConfiguration {

    @Value("${HOST_LOCAL}")
    private String localEnv;
    @Value("${HOST_AWS}")
    private String hostedEnv;
    @Value("${ADMIN_USERNAME}")
    private String adminUsername;
    @Value("${ADMIN_PASS}")
    private String adminPass;
    @Value("${GUEST_USERNAME}")
    private String guestUsername;
    @Value("${GUEST_PASS}")
    private String guestPass;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(localEnv, hostedEnv));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setMaxAge(3600L);
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // create security config
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails rafael = User.builder()
                .username(adminUsername)
                .password(adminPass)
                .roles("ADMIN")
                .build();

        UserDetails guest = User.builder()
                .username(guestUsername)
                .password(guestPass)
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(rafael, guest);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((configurer) -> configurer
                        .antMatchers("/api/v2/**").hasRole("GUEST")
                        .anyRequest()
                        .authenticated())
                .httpBasic();

        http.cors(); // adds Spring-provided CorsFilter to app context. Bypasses checks for OPTIONS request

        // disable Cross Site Request Forgery (CSRF)
        http.csrf().disable();

        return http.build();
    }

    // projection factory
    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }
}
