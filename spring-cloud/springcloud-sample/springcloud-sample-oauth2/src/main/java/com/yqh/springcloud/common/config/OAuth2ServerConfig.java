package com.yqh.springcloud.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yangq
 * Create time in 2018-08-01 13:58
 */
@Configuration
public class OAuth2ServerConfig {
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().
                    exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)).
                    and().authorizeRequests().anyRequest().authenticated().
                    and().httpBasic();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;
        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Bean
        public RedisTokenStore tokenStore() {
            return new RedisTokenStore(redisConnectionFactory);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory().withClient("client_test")
                    .secret(passwordEncoder.encode("123456"))
                    .authorizedGrantTypes("all flow")
                    .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token", "password", "implicit")
                    .scopes("all", "read", "write")
                    .accessTokenValiditySeconds(72000)
                    .refreshTokenValiditySeconds(72000);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.tokenStore(tokenStore())
                    .userDetailsService(userDetailsService)
                    .authenticationManager(authenticationManager).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }

        //允许表单认证
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer.tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()")
                    .allowFormAuthenticationForClients();
        }
    }
}
