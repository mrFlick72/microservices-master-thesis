package it.valeriovaudi.emarket.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by vvaudi on 28/05/17.
 */
public interface AuthServerAccountServiceBridge {

    @Output("authServerAccountServiceBridgeInbounbdChannel")
    DirectChannel authServerAccountServiceBridgeInbounbdChannel();

    @Input("authServerAccountServiceBridgeOutboundChannel")
    DirectChannel authServerAccountServiceBridgeOutboundChannel();

}
