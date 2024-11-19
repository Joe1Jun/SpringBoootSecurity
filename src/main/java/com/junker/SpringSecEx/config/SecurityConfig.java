package com.junker.SpringSecEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this class as a configuration class for Spring, allowing it to contain bean definitions
@EnableWebSecurity // Enables Spring Security for the application, making this the main configuration class for security
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Bean // Marks this method as a Spring Bean, which means Spring will call it to instantiate the object
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // This method configures security for HTTP requests

        // Configures CSRF (Cross-Site Request Forgery) protection
      return  http
        .csrf(customizer -> customizer.disable()) // Disables CSRF protection (often used in stateless APIs where CSRF is unnecessary)

        // Configures HTTP request authorization
        .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // Secures all requests by requiring authentication
        // By default, all requests require authentication. You can change this to specify more granular rules.

        // Configures HTTP Basic Authentication (often used for APIs)
        .httpBasic(Customizer.withDefaults()) // Enables HTTP Basic authentication. This means that users will need to send their credentials (username & password) in the HTTP header.

        // Configures session management
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensures the session is stateless, meaning Spring Security will not use a session to track user state (useful in RESTful APIs that use JWT tokens or other stateless authentication mechanisms)

        // Return the fully configured SecurityFilterChain to Spring
        .build(); // The `build()` method compiles all the configurations and returns the `SecurityFilterChain` object
    
    /*
     * Stateless Applications: If you're building a RESTful 
     * API or using JWT for authentication, this configuration ensures that the application doesn't use sessions, which is in line with best practices for REST APIs.
         Security Customization: Disabling CSRF protection, 
         using HTTP Basic Authentication, and other configurations 
         allow you to tailor the security to your application's needs.
     */
    
    
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public AuthenticationProvider authenticationProvider () {
    	
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
      provider.setUserDetailsService(userDetailsService);
     
      return provider;
    }
    
//    @Bean
//    public UserDetailsService userDetailsService() {
//    	
//    	UserDetails user1 = User
//    			.withDefaultPasswordEncoder()
//    			.username("kieran")
//    			.password("k@123")
//    			.roles("USER")
//    			.build();
//    	
//    	return new InMemoryUserDetailsManager(user1);
//    	
//    }
    
    
}



