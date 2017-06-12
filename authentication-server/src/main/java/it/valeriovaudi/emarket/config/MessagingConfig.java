package it.valeriovaudi.emarket.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.channel.MessageChannels;


/**
 * Created by vvaudi on 12/06/17.
 */

@Configuration
public class MessagingConfig {

    @Bean("authServerAccountServiceBridgeInbounbdChannel")
    public DirectChannel authServerAccountServiceBridgeInbounbdChannel(){
        return MessageChannels.direct().get();
    }

    @Bean("authServerAccountServiceBridgeOutboundChannel")
    public DirectChannel authServerAccountServiceBridgeOutboundChannel(){
        return MessageChannels.direct().get();
    }

    @Bean("authServerAccountServiceBridgeInbounbdQueue")
    public Queue authServerAccountServiceBridgeInbounbdQueue(){
        return new Queue("authServerAccountServiceBridgeInbounbdQueue", false);
    }

    @Bean("authServerAccountServiceBridgeOutboundQueue")
    public Queue authServerAccountServiceBridgeOutboundQueue(){
        return new Queue("authServerAccountServiceBridgeOutboundQueue", false);
    }
}
