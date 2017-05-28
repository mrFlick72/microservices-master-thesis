package it.valeriovaudi.emarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by vvaudi on 27/05/17.
 */

@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private OAuth2ClientContext context;

    @Bean
    @LoadBalanced
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails authorizationCodeResourceDetails){
        return new OAuth2RestTemplate(authorizationCodeResourceDetails, context);
    }
}