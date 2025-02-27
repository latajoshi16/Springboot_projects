package com.test.java.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {

                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN,USER");
                    authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).csrf((csrf) -> csrf.disable());
        return http.build();

    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails appUser = User.builder()
                .username("appuser1")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(appUser,adminUser);
    }
}
