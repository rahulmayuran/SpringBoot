package com.stock.market.User.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1.0/user/**").hasRole(Constants.ADMIN)
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails rahul = User.builder()
                .username("rahul")
                .password(passwordEncoder.encode("rahul"))
                .roles(Constants.ADMIN)
                .build();

        return new InMemoryUserDetailsManager(rahul);
    }
}
