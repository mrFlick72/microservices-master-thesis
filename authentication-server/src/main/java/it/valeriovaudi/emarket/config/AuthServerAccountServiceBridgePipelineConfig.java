package it.valeriovaudi.emarket.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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

// todo fixme
@Configuration
public class AuthServerAccountServiceBridgePipelineConfig {


    @Autowired
    private ConnectionFactory rabbitConnectionFactory;


    @Bean
    public IntegrationFlow getUserDetailsIntegrationPipelineConfig(DirectChannel authServerAccountServiceBridgeInbounbdChannel,
                                                                   Queue authServerAccountServiceBridgeInbounbdQueue){
        return IntegrationFlows.from(authServerAccountServiceBridgeInbounbdChannel)
                .gateway(IntegrationFlows.from(Amqp.inboundGateway(rabbitConnectionFactory,
                        authServerAccountServiceBridgeInbounbdQueue)).get())
                .get();
    }

    @Bean
    public IntegrationFlow getUserDetailsIntegrationReturnPipelineConfig(DirectChannel authServerAccountServiceBridgeInbounbdChannel,
                                                                   Queue authServerAccountServiceBridgeInbounbdQueue){
        return IntegrationFlows.from(authServerAccountServiceBridgeInbounbdChannel)
                .gateway(IntegrationFlows.from(Amqp.inboundGateway(rabbitConnectionFactory,
                        authServerAccountServiceBridgeInbounbdQueue)).get())
                .get();
    }
}
