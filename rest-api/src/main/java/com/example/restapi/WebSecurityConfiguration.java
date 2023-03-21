package com.example.restapi;

import kn.knlogin.visco.restaccount.spring.security.CookieAccessor;
import kn.knlogin.visco.restaccount.spring.security.ViscoUserDetailsService;
import kn.knlogin.visco.restaccount.spring.security.ViscoWebSecurityConfiguration;
import kn.knlogin.visco.restaccount.spring.security.WebAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebSecurityConfiguration extends ViscoWebSecurityConfiguration {

    public WebSecurityConfiguration(
            CookieAccessor cookieAccessor,
            ViscoUserDetailsService userDetailsService,
            WebAuthenticationEntryPoint webAuthenticationEntryPoint) {
        super(cookieAccessor, userDetailsService, webAuthenticationEntryPoint);
    }

    @Override
    protected void configure(final HttpSecurity http, final AuthenticationProvider authenticationProvider) throws Exception {
        http.cors();
        http.csrf().disable();
    }

    @Override
    protected void configureAuthorizeHttpRequests(HttpSecurity http) throws Exception {
        super.configureAuthorizeHttpRequests(http);
        http.authorizeHttpRequests().anyRequest().permitAll();
    }

    @Bean
    public StrictHttpFirewall configureFirewall() {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        strictHttpFirewall.setAllowSemicolon(true);
        return strictHttpFirewall;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Access-Control-Allow-Origin", "Origin", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
