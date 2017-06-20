package it.valeriovaudi.emarket.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;

/**
 * Created by vvaudi on 12/06/17.
 */

@Configuration
public class AuthServerAccountServiceBridgePipelineConfig {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public IntegrationFlow getUserDetailsIntegrationPipelineConfig(DirectChannel authServerAccountServiceBridgeInboundChannel,
                                                                   DirectChannel authServerAccountServiceBridgeOutboundChannel) {
        return IntegrationFlows.from(authServerAccountServiceBridgeInboundChannel)
                .handle(Amqp.outboundGateway(rabbitTemplate)
                        .routingKey("authServerAccountServiceBridgeInboundQueue")
                        .returnChannel(authServerAccountServiceBridgeOutboundChannel))
                .get();
    }
}
