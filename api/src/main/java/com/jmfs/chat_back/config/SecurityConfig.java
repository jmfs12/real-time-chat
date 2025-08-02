package com.jmfs.chat_back.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jmfs.chat_back.security.CustomUserDetailsService;
import com.jmfs.chat_back.security.SecurityFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
      @Autowired
      SecurityFilter securityFilter;

      @Autowired
      CustomUserDetailsService customUserDetailsService;

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          log.info("[SECURITY] Configuring security filter chain");
          http
              .csrf(csrf -> csrf.disable())
              .cors(cors -> cors.configurationSource(corsConfigurationSource(http))) // <--- ESSENCIAL
              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(authorize -> authorize
                  .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                  .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                  .anyRequest().authenticated())
              .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
          log.info("[SECURITY] Security filter chain configured successfully");
          return http.build();
      }

      @Bean
      protected CorsConfigurationSource corsConfigurationSource(HttpSecurity http){
            log.info("[SECURITY] Configuring HTTP security");
            CorsConfiguration config = new CorsConfiguration();

            config.setAllowedOrigins(List.of("http://localhost:5173/"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(true); 

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);

            return source;
      }
      
      @Bean
      public PasswordEncoder PasswordEncoder() {
            log.info("[SECURITY] Configuring password encoder");
            return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                  throws Exception {
            log.info("[SECURITY] Configuring authentication manager");
            return authenticationConfiguration.getAuthenticationManager();
      }


}
