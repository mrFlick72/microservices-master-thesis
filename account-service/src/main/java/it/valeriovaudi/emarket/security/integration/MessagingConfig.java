package it.valeriovaudi.emarket.security.integration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by vvaudi on 12/06/17.
 */

@Configuration
public class MessagingConfig {

    @Bean("authServerAccountServiceBridgeInboundQueue")
    public Queue authServerAccountServiceBridgeInboundQueue(){
        return new Queue("authServerAccountServiceBridgeInboundQueue", false, false, true);
    }

}
