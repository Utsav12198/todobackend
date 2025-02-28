package com.todoapp.todo_backend.config;
import com.todoapp.todo_backend.users.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//The SecurityConfig class (often referred to as the Security Configuration class) is a central part of your Spring Security setup.
//Its main purpose is to configure security-related behaviors for your application, such as authentication, authorization, session management, and CSRF protection.

@Configuration
@EnableWebSecurity
@EnableMethodSecurity    //for role based security. i.e when we make use of @PreAuthorize(hasRole()) annotation for a particular controller that user with specific roles can only access it.
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Only allow users with 'ADMIN' role to access these endpoints
                        .requestMatchers("/user/**").hasRole("USER")    // Only allow users with 'USER' role to access these endpoints
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/registerpage").permitAll()
                        .anyRequest().authenticated()   // All other requests need to be authenticated
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection globally
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(withDefaults())  // Default form-based login (Spring Security 5+ provides defaults)
                .httpBasic(withDefaults())  // Default basic authentication (Spring Security 5+ provides defaults)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/signup")  // Disable CSRF for /signup only
                );
        return http.build();  // Return the built SecurityFilterChain object
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    //SpringBootWebSecurityConfiguration: this class provides the default security behaviour when we include dependencies in pom but we are using this java class to provide our security features
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //no cookies maintained for session management (As rest is stateless no need of stateful)
////        http.formLogin(withDefaults());  //forms
//        http.httpBasic(withDefaults());  //pop up
//        return http.build();
//    }
//
//    //for in-memory authentication// the users stored are non persistent when terminated does not exist
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1 = User.withUsername("user1")
//                .password("{noop}password1")
//                .roles("USER")    //for role based authentication
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}adminPass")         //since its inmemory we tell Spring security node to encode password and keep it plain text
//                .roles("ADMIN")
//                .build();
//s
//        return new InMemoryUserDetailsManager(user1,admin);     //UserDetailsObject meed to be passed as parameter
//    }

}
