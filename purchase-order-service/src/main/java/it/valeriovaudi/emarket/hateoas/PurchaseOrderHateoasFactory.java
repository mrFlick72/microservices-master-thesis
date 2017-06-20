package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.PurchaseOrderRestFullEndPoint;
import it.valeriovaudi.emarket.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static it.valeriovaudi.emarket.hateoas.AbstractHateoasFactoryConstants.*;
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

    public Resource<PurchaseOrder> toResource(PurchaseOrder purchaseOrder){
        String orderNumber = purchaseOrder.getOrderNumber();
        Resource<PurchaseOrder> resource = new Resource<>(purchaseOrder);

        resource.add(gertPurchaseOrderSelfLink(orderNumber));

        resource.add(customerHateoasFactory.getCustomerSelfLink(orderNumber).withRel(CUSTOMER_LINK_KEY));
        resource.add(deliveryHateoasFactory.getDeliverySelfLink(orderNumber).withRel(DELIVERY_LINK_KEY));
        resource.add(shipmentHateoasFactory.getShipmentSelfLink(orderNumber).withRel(SHIPMENT_LINK_KEY));

        resource.add(goodsInPurchaseOrderHateoasFactory.getGoodsInPurchaseOrderSelfLink(orderNumber)
                .withRel(GOODS_IN_PURCHASE_ORDER_LIST_LINK_KEY));

        resource.add(goodsInPurchaseOrderHateoasFactory.getGoodsInPurchaseOrderLink(orderNumber)
                .withRel(GOODS_IN_PURCHASE_ORDER_LINK_KEY));

        return resource;
    }

    public Resources<PurchaseOrder> toResources(List<PurchaseOrder> purchaseOrderList){
        Resources<PurchaseOrder> resources = new Resources<>(purchaseOrderList);
        resources.add(gertPurchaseOrderListSelfLink());
        return resources;
    }

    public Link gertPurchaseOrderSelfLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(PurchaseOrderRestFullEndPoint.class)
                .getPuchaseOrder(orderNumber))
                .withSelfRel();
    }

    public Link gertPurchaseOrderListSelfLink(){
        return linkTo(PurchaseOrderRestFullEndPoint.class)
                .withSelfRel();
    }
}
