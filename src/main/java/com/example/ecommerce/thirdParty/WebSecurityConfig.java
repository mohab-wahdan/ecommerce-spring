package com.example.ecommerce.thirdParty;
import com.example.ecommerce.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login") // Specify custom login page
                .defaultSuccessUrl("/home") // Redirect to home after successful login
                .failureUrl("/login?error=true") // Redirect to login with error on failure
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Allow sessions to be created even for anonymous users
                .maximumSessions(1) // Optional: Limit to one session per user
                .expiredUrl("/login?expired=true");

        return http.build();
    }
    @Bean
    public WebMvcConfigurer resourceConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/")
                        .setCachePeriod(3600);
            }
        };
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // Allow sessions to be created when necessary
                .invalidSessionUrl("/login?sessionExpired=true")  // Redirect to a page when the session is invalid
                .and()
                .authorizeRequests()
                .requestMatchers("/", "/home").permitAll()
                .anyRequest().authenticated();
    }

}

