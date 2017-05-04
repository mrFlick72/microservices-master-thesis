package it.valeriovaudi.emarket.event.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.channel.PublishSubscribeChannel;

/**
 * Created by mrflick72 on 04/05/17.
 */
public interface EventMessageChannels {

    @Input("accountEventOutboundChannel")
    PublishSubscribeChannel accountEventOutboundChannel();
}
