package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.PurchaseOrderRestFullEndPoint;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by mrflick72 on 15/06/17.
 */

@Component
public class PurchaseOrderHateoasFactory {

    public Link gertPurchaseOrderLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(PurchaseOrderRestFullEndPoint.class)
                .getPuchaseOrder(orderNumber))
                .withSelfRel();

    }
}
