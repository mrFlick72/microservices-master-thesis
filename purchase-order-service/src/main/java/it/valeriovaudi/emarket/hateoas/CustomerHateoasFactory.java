package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.response.CustomerDataResponseDTO;
import it.valeriovaudi.emarket.endpoint.restfull.CustomerRestFullEndPoint;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by mrflick72 on 15/06/17.
 */

@Data
@Component
public class CustomerHateoasFactory {

    public static final String PURCHASE_ORDER_LINK_KEY = "purchase-order";

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    public Resource<CustomerDataResponseDTO> toResource(String orderNumber,
                                                        CustomerDataResponseDTO customerDataResponseDTO){

        Resource<CustomerDataResponseDTO> resource = new Resource<>(customerDataResponseDTO);

        Link link = linkTo(ControllerLinkBuilder.methodOn(CustomerRestFullEndPoint.class)
                .getCustomerDataPuchaseOrder(orderNumber))
                .withSelfRel();

        resource.add(link);
        resource.add(purchaseOrderHateoasFactory.gertPurchaseOrderLink(orderNumber).withRel(PURCHASE_ORDER_LINK_KEY));

        return resource;
    }

}
