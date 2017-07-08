package it.valeriovaudi.emarket.event.service;

import it.valeriovaudi.emarket.event.config.EventMessageChannels;
import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.model.*;
import it.valeriovaudi.emarket.event.repository.GoodsErrorEventRepository;
import it.valeriovaudi.emarket.event.repository.GoodsEventRepository;
import it.valeriovaudi.emarket.event.repository.PriceListErrorEventRepository;
import it.valeriovaudi.emarket.event.repository.PriceListEventRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by mrflick72 on 04/05/17.
 */

@Data
@Service
public class EventDomainPubblishService {

    @Autowired
    private DomainEventFactory domainEventFactory;

    @Autowired
    private GoodsEventRepository goodsEventRepository;

    @Autowired
    private GoodsErrorEventRepository goodsErrorEventRepository;

    @Autowired
    private PriceListEventRepository priceListEventRepository;

    @Autowired
    private PriceListErrorEventRepository priceListErrorEventRepository;

    @Autowired
    private SubscribableChannel goodsEventOutboundChannel;

    @Autowired
    private SubscribableChannel priceListEventOutboundChannel;

    public GoodsEvent publishGoodsEvent(String correlationId, String idGoods, String name, String barCode,
                                        String category, EventTypeEnum type){
        GoodsEvent event = domainEventFactory.newGoodsEvent(correlationId, idGoods, name, barCode, category, type);
        goodsEventRepository.save(event);
        goodsEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public GoodsErrorEvent publishGoodsErrorEvent(String correlationId, String idGoods, String name, String barCode,
                                                  String category, EventTypeEnum type,
                                                  String message, Class exceptionClassName){
        GoodsErrorEvent event = domainEventFactory.newGoodsErrorEvent(correlationId, idGoods, name, barCode, category, type,message,exceptionClassName);
        goodsErrorEventRepository.save(event);
        goodsEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public PriceListEvent publishPriceListEvent(String correlationId, String idPriceList,
                                                String name, EventTypeEnum type){
        PriceListEvent event = domainEventFactory.newPriceListEvent(correlationId, idPriceList, name, type);
        priceListEventRepository.save(event);
        priceListEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public PriceListErrorEvent publishPriceListErrorEvent(String correlationId, String idPriceList,
                                                          String name, EventTypeEnum type,
                                                          String message, Class exceptionClassName){
        PriceListErrorEvent event = domainEventFactory.newPriceListErrorEvent(correlationId, idPriceList, name, type, message, exceptionClassName);
        priceListErrorEventRepository.save(event);
        priceListEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

}