package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.PurchaseOrderRestFullEndPoint;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by mrflick72 on 15/06/17.
 */

@Component
public class PurchaseOrderHateoasFactory {

    @Autowired
    private CustomerHateoasFactory customerHateoasFactory;

    @Autowired
    private DeliveryHateoasFactory deliveryHateoasFactory;

    @Autowired
    private GoodsInPurchaseOrderHateoasFactory goodsInPurchaseOrderHateoasFactory;

    @Autowired
    private ShipmentHateoasFactory shipmentHateoasFactory;


    public Link gertPurchaseOrderSelfLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(PurchaseOrderRestFullEndPoint.class)
                .getPuchaseOrder(orderNumber))
                .withSelfRel();
    }
}
