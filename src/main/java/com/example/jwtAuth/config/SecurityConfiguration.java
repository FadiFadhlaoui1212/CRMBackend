package com.example.jwtAuth.config;

import com.example.jwtAuth.auth.JwtAuthenticationFilter;
import com.example.jwtAuth.repository.RoleRepository;
import com.example.jwtAuth.service.implementation.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        AuthenticationManager authenticationManager = builder.build();

        return http
                .cors().and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(GET, "/api/v1/testAdmission/allUsers").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(GET, "/api/v1/testAdmission/onlyAdmin").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(POST, "/api/contact/create").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(GET, "/api/contact/contacts").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(GET, "/api/contact/delete/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(PUT, "/api/contact/update/{id}").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers(POST, "/api/contact/{id}/upload-picture").permitAll()
                .antMatchers("/api/contact/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/document/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationManager(authenticationManager)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {  // ✅ Fixed CORS Configuration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ Allow Angular Frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}