package it.valeriovaudi.emarket.event.service;

import it.valeriovaudi.emarket.event.config.EventMessageChannels;
import it.valeriovaudi.emarket.event.factory.DomainEventFactory;
import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.event.model.PurchaseOrderErrorEvent;
import it.valeriovaudi.emarket.event.model.PurchaseOrderEvent;
import it.valeriovaudi.emarket.event.repository.PurchaseOrderErrorEventRepository;
import it.valeriovaudi.emarket.event.repository.PurchaseOrderEventRepository;
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
    public PurchaseOrderEventRepository purchaseOrderEventRepository;

    @Autowired
    public PurchaseOrderErrorEventRepository purchaseOrderErrorEventRepository;

    @Autowired
    private SubscribableChannel purchaseOrderEventOutboundChannel;

    public PurchaseOrderEvent publishPurchaseOrderEvent(String correlationId, String idPurchaseOrder,
                                                             String idProductCatalogId, String idGoodsInPurchaseOrder,
                                                             String customerUserName, EventTypeEnum type){
        PurchaseOrderEvent event = domainEventFactory.newPurchaseOrderEvent(correlationId, idPurchaseOrder,
                                                            idProductCatalogId, idGoodsInPurchaseOrder,
                                                            customerUserName, type);
        purchaseOrderEventRepository.save(event);
        purchaseOrderEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public PurchaseOrderErrorEvent publishPurchaseOrderErrorEvent(String correlationId, String idPurchaseOrder,
                                                                  String idProductCatalogId, String idGoodsInPurchaseOrder,
                                                                  String customerUserName, EventTypeEnum type,
                                                                  String message, Class exceptionClassName){
        PurchaseOrderErrorEvent event = domainEventFactory.newPurchaseOrderErrorEvent(correlationId, idPurchaseOrder,
                idProductCatalogId, idGoodsInPurchaseOrder,
                customerUserName, type,message, exceptionClassName);

        purchaseOrderErrorEventRepository.save(event);
        purchaseOrderEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

}