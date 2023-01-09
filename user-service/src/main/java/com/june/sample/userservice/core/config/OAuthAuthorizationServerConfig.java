package com.june.sample.userservice.core.config;

import com.june.sample.userservice.user.service.UserService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String JWT_SECRET = "sample-userService";

    @Autowired
    private UserService userDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Value("${security.jwt.token.expires-in}")
    private int tokenExpiresIn;

    @Value("${security.jwt.refresh-token.expires-in}")
    private int refreshTokenExpiresIn;

    /**
     * JWT signingKey 설정
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_SECRET);
        return converter;
    }

    /**
     * tokenStore 정의
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * tokenService 정의
     */
    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * configure
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService)     // refresh_token을 위해서 반드시 필요.
        ;
    }

    /**
     * oauth2 client detail 설정은 고정된 inMemory 방식으로.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("front-ui").secret("$2a$10$ltegD526z3lu.LolF4QDB.0KrSDg8pvLzxH5cJDE4rpGEUcwDMgoq") // front-ui : front-ui!
                .scopes("read", "write")
                .authorizedGrantTypes("client_credentials","password", "refresh_token")
                .accessTokenValiditySeconds((int)TimeUnit.MINUTES.toSeconds(tokenExpiresIn))
                .refreshTokenValiditySeconds((int)TimeUnit.MINUTES.toSeconds(refreshTokenExpiresIn));

    }

}
