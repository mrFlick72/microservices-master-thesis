package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.response.CustomerDataResponseDTO;
import it.valeriovaudi.emarket.endpoint.restfull.CustomerRestFullEndPoint;
import lombok.Data;
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

@Data
@Component
public class CustomerHateoasFactory {

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    public Resource<CustomerDataResponseDTO> toResource(String orderNumber,
                                                        CustomerDataResponseDTO customerDataResponseDTO){

        Resource<CustomerDataResponseDTO> resource = new Resource<>(customerDataResponseDTO);

        resource.add(getCustomerSelfLink(orderNumber));
        resource.add(purchaseOrderHateoasFactory.gertPurchaseOrderSelfLink(orderNumber).withRel(PURCHASE_ORDER_LINK_KEY));

        return resource;
    }

    public Link getCustomerSelfLink(String orderNumber){
        return  linkTo(ControllerLinkBuilder.methodOn(CustomerRestFullEndPoint.class)
                .getCustomerDataPuchaseOrder(orderNumber))
                .withSelfRel();
    }

}
