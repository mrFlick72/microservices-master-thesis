package it.valeriovaudi.emarket.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by mrflick72 on 12/06/17.
 */

@MessagingGateway
public interface LogInRequestGateway {

    @Gateway(requestChannel = "authServerAccountServiceBridgeInboundChannel",
            replyChannel = "authServerAccountServiceBridgeOutboundChannel",
            replyTimeout = 60*1000)
    UserDetails getPrincipleByUSerName(String userName);
}
