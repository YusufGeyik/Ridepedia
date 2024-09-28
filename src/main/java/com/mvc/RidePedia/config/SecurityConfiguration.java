package com.mvc.RidePedia.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
 {
     http.authorizeHttpRequests(req->req.requestMatchers("/api/ridepedia/auth/**").permitAll().anyRequest().authenticated());

/*

     http.authorizeHttpRequests(req->req.requestMatchers("/api/ridepedia/auth/**").permitAll().requestMatchers("/api/ridepedia").hasRole("ADMIN").anyRequest().authenticated());
*/

    http  .csrf(AbstractHttpConfigurer::disable);

     return http.build();


 }

}
