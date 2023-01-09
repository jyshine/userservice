package com.june.sample.userservice.core.security;

import com.june.sample.userservice.user.service.UserService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${security.cors.allowed-origins:}")
    private String[] corsAllowedOrigins;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * web security 설정
     */
    @Override
    public void configure(WebSecurity web) {
        // TokenAuthenticationFilter will ignore the below paths
        web.ignoring().antMatchers(
                "/ping"
        );
        web.ignoring().antMatchers(
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );
    }

    /**
     * CORS 설정.
     */
    @Bean
    public FilterRegistrationBean customCorsFilter() {
        // oauth 환경에서 cors설정은 configure( HttpSecurity ) 로는 가능하지 않고,
        // cors 필터가 다른 oauth 설정보다 선행하도록 하기 위해서, 별도의 FilterRegistrationBean 을 사용함.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        log.info("cors.allowed-origins={}", (Object)corsAllowedOrigins);
        if (corsAllowedOrigins != null) {
            config.setAllowedOrigins(Arrays.asList(corsAllowedOrigins));
        }

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/oauth/**", config);
        source.registerCorsConfiguration("/api/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    // 참고: https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/cors.html
    // 참고: https://stackoverflow.com/questions/44427171/cors-interfering-with-spring-security-oauth2


}
