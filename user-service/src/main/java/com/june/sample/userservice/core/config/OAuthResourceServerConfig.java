package com.june.sample.userservice.core.config;

import com.june.sample.userservice.core.enums.user.UserRoleType;
import com.june.sample.userservice.core.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("sample-userservice")
                .tokenServices(tokenServices)
                .tokenStore(tokenStore)
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST , Path.API+ Path.VERSION+"/users",
                        Path.API+Path.VERSION+"/users/sendCode",Path.USERS_SEND_CODE_CHECK,"/api/v1/changePassword/").not().authenticated()
                .antMatchers(Path.API+Path.VERSION+"/**")
                .hasAnyAuthority(UserRoleType.M.name(),UserRoleType.A.name(),UserRoleType.U.name())
                ;
    }
}
