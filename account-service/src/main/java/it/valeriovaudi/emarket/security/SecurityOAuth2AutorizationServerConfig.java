package it.valeriovaudi.emarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Configuration
@EnableAuthorizationServer
public class SecurityOAuth2AutorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .approvalStoreDisabled()
                .userDetailsService(accountUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
//                .and().formLogin().failureUrl("/login");
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("my-trusted-client")
                .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_USER")
                .scopes("read", "write", "trust")
                .resourceIds("oauth2-resource")
                .autoApprove(true)
                .redirectUris("http://localhost:5050/v1/account-service/login")
                .accessTokenValiditySeconds(600);

				/*.and()
				.withClient("my-client-with-registered-redirect")
				.authorizedGrantTypes("authorization_code")
				.authorities("ROLE_CLIENT", "ROLE_USER")
				.scopes("read", "trust")
                .autoApprove(true)
                .accessTokenValiditySeconds(600)
				.resourceIds("oauth2-resource")
				.redirectUris("http://localhost:9090/login")

				.and()
				.withClient("my-client-with-secret")
				.authorizedGrantTypes("client_credentials", "password")
				.authorities("ROLE_CLIENT", "ROLE_USER")
				.scopes("read")
				.resourceIds("oauth2-resource")
				.secret("secret");*/
    }
}
