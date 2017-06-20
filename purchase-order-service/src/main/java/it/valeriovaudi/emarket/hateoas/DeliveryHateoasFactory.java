package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.CustomerRestFullEndPoint;
import it.valeriovaudi.emarket.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static it.valeriovaudi.emarket.hateoas.AbstractHateoasFactoryConstants.PURCHASE_ORDER_LINK_KEY;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by mrflick72 on 15/06/17.
 */

@Component
public class DeliveryHateoasFactory {

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    public Resource<Delivery> toResource(String orderNumber, Delivery delivery){
        Resource<Delivery> resource = new Resource<>(delivery);

        resource.add(getDeliverySelfLink(orderNumber));
        resource.add(purchaseOrderHateoasFactory.gertPurchaseOrderSelfLink(orderNumber).withRel(PURCHASE_ORDER_LINK_KEY));

        return resource;
    }

    public Link getDeliverySelfLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(CustomerRestFullEndPoint.class)
                .getCustomerDataPuchaseOrder(orderNumber))
                .withSelfRel();
    }
}
