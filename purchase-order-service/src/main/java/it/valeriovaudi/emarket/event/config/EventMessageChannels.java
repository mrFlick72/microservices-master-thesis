package it.valeriovaudi.emarket.event.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by mrflick72 on 04/05/17.
 */
public interface EventMessageChannels {

    @Output("purchaseOrderEventOutboundChannel")
    SubscribableChannel purchaseOrderEventOutboundChannel();

}
