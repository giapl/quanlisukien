package org.example.quanlisukien.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/auth/register", HttpMethod.POST.name()))
            .permitAll()
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/auth/login", HttpMethod.POST.name())).permitAll()
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/all", HttpMethod.GET.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/search/id", HttpMethod.GET.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/search", HttpMethod.GET.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/delete", HttpMethod.DELETE.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/update", HttpMethod.PUT.name()))
            .hasAnyAuthority("USER", "ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/update/admin", HttpMethod.PUT.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/roles/create", HttpMethod.POST.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/v1/roles/all", HttpMethod.GET.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/roles/delete", HttpMethod.DELETE.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/roles/update", HttpMethod.PUT.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/categories/create", HttpMethod.POST.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/categories/all", HttpMethod.GET.name()))
            .hasAnyAuthority("USER", "ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/categories/delete", HttpMethod.DELETE.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/categories/update", HttpMethod.PUT.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/accounts/create/admin", HttpMethod.POST.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/events/create", HttpMethod.POST.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(new AntPathRequestMatcher("/api/v1/events/all", HttpMethod.GET.name()))
            .hasAnyAuthority("ADMIN", "USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/events/delete", HttpMethod.DELETE.name()))
            .hasAuthority("ADMIN")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/events/update", HttpMethod.PUT.name()))
            .hasAuthority("ADMIN")
            .anyRequest()
            .denyAll())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

}
