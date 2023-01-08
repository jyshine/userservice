package com.june.sample.userservice.core.security;

import com.june.sample.userservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    Environment env;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = getAuthenticationFilter(http);
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/**")
                .hasIpAddress("127.0.0.1")
                .and()
                .authenticationManager(authenticationManager)
                .addFilter(getAuthenticationFilter(authenticationManager));
        return http.build();
    }

    private AuthenticationManager getAuthenticationFilter(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager,userService,env);
    }

}
