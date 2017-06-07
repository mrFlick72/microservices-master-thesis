package it.valeriovaudi.emarket.event.factory;

import com.datastax.driver.core.utils.UUIDs;
import it.valeriovaudi.emarket.event.model.EventAuditData;
import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.event.model.PurchaseOrderErrorEvent;
import it.valeriovaudi.emarket.event.model.PurchaseOrderEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mrflick72 on 07/06/17.
 */

@Component
public class DomainEventFactory {

    public PurchaseOrderEvent newPurchaseOrderEvent(String correlationId, String idPurchaseOrder,
                                                    String idProductCatalog, String idGoodsInPurchaseOrder,
                                                    String customerUserName, EventTypeEnum type){
        PurchaseOrderEvent event = new PurchaseOrderEvent();
        event.setId(UUIDs.timeBased());
        event.setCustomerUserName(customerUserName);
        event.setIdProductCatalog(idProductCatalog);
        event.setIdPurchaseOrder(idPurchaseOrder);
        event.setIdGoodsInPurchaseOrder(idGoodsInPurchaseOrder);
        event.setType(type);

        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public PurchaseOrderErrorEvent newPurchaseOrderErrorEvent(String correlationId, String idPurchaseOrder,
                                                              String idProductCatalog, String idGoodsInPurchaseOrder,
                                                              String customerUserName, EventTypeEnum type,
                                                              String message, Class exceptionClassName){
        PurchaseOrderErrorEvent event = new PurchaseOrderErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setCustomerUserName(customerUserName);
        event.setIdProductCatalog(idProductCatalog);
        event.setIdPurchaseOrder(idPurchaseOrder);
        event.setIdGoodsInPurchaseOrder(idGoodsInPurchaseOrder);

        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }


    private EventAuditData newEventAuditData(String correlationId){
        EventAuditData eventAuditData = new EventAuditData();

        eventAuditData.setCorrelationId(correlationId);
        eventAuditData.setUserName("");
        eventAuditData.setTimeStamp(new Date());

        return eventAuditData;
    }
}
