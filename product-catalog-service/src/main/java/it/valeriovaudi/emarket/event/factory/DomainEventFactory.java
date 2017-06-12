package it.valeriovaudi.emarket.event.factory;

import com.datastax.driver.core.utils.UUIDs;
import it.valeriovaudi.emarket.event.model.*;
import it.valeriovaudi.emarket.security.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by mrflick72 on 03/05/17.
 */

@Data
@Component
public class DomainEventFactory {

    @Autowired
    private SecurityUtils securityUtils;

    public GoodsEvent newGoodsEvent(String correlationId, String idGoods, String name, String barCode,
                                    String category, EventTypeEnum type){
        GoodsEvent event = new GoodsEvent();
        event.setId(UUIDs.timeBased());
        event.setBarCode(barCode);
        event.setName(name);
        event.setCategory(category);
        event.setIdGoods(idGoods);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public GoodsErrorEvent newGoodsErrorEvent(String correlationId, String idGoods, String name, String barCode,
                                         String category, EventTypeEnum type,
                                         String message, Class exceptionClassName){
        GoodsErrorEvent event = new GoodsErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setBarCode(barCode);
        event.setName(name);
        event.setCategory(category);
        event.setIdGoods(idGoods);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }

    public PriceListEvent newPriceListEvent(String correlationId, String idPriceList,
                                            String name, EventTypeEnum type){
        PriceListEvent event = new PriceListEvent();
        event.setId(UUIDs.timeBased());
        event.setIdPriceList(idPriceList);
        event.setName(name);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public PriceListErrorEvent newPriceListErrorEvent(String correlationId, String idPriceList,
                                                     String name, EventTypeEnum type,
                                                     String message, Class exceptionClassName){
        PriceListErrorEvent event = new PriceListErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setIdPriceList(idPriceList);
        event.setName(name);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }


    private EventAuditData newEventAuditData(String correlationId){
        EventAuditData eventAuditData = new EventAuditData();

        eventAuditData.setCorrelationId(correlationId);
        eventAuditData.setUserName(securityUtils.getPrincipalUserName());
        eventAuditData.setTimeStamp(new Date());

        return eventAuditData;
    }
}
