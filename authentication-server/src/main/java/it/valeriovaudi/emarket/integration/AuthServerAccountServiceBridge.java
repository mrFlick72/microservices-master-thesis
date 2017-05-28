package it.valeriovaudi.emarket.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by vvaudi on 28/05/17.
 */
public interface AuthServerAccountServiceBridge {

    @Output("authServerAccountServiceBridgeChannel")
    SubscribableChannel authServerAccountServiceBridgeChannelsu();

}
