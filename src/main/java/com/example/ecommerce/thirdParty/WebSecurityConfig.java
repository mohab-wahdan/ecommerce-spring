package com.example.ecommerce.thirdParty;

import com.example.ecommerce.security.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register", "/login", "/grantcode").permitAll() // Allow access to these endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity, adjust as needed for security
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Manage sessions
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login") // Specify custom login page
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                Authentication authentication) throws IOException, ServletException {
                                // Process OAuth login success
                                userService.processOAuthPostLogin(authentication.getName());
                                response.sendRedirect("/home"); // Redirect to home page after successful login
                            }
                        })
                )
                .addFilterAfter(bearerTokenAuthFilter(), BasicAuthenticationFilter.class); // Add bearer token filter
        return http.build();
    }

    @Bean
    public BearerTokenAuthFilter bearerTokenAuthFilter() {
        return new BearerTokenAuthFilter(); // Ensure this matches the class name and package
    }
}
