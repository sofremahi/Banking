package com.TIDDEV.mhn.banking.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
// letting all people reach any endpoint
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/rest/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic(); // enable basic authentication
//    }
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/rest/banking/account/find").hasAnyRole("USER", "ADMIN") // first
            .antMatchers("/rest/**").hasRole("ADMIN") // last
            .anyRequest().authenticated()
            .and()
            .httpBasic();
}
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user , admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
