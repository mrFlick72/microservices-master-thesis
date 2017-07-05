package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.GoodsInPurchaseOrderRestFullEndPoint;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

import static it.valeriovaudi.emarket.hateoas.AbstractHateoasFactoryConstants.GOODS_IN_PURCHASE_ORDER_LINK_KEY;
import static it.valeriovaudi.emarket.hateoas.AbstractHateoasFactoryConstants.PURCHASE_ORDER_LINK_KEY;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by mrflick72 on 15/06/17.
 */

@Component
public class GoodsInPurchaseOrderHateoasFactory {

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    public Resources<Goods> toResources(String orderNumber, List<Goods> goods){
        Resources<Goods> resources = new Resources<>(goods);

        resources.add(getGoodsInPurchaseOrderSelfLink(orderNumber));
        resources.add(getGoodsInPurchaseOrderLink(orderNumber));
        resources.add(purchaseOrderHateoasFactory.gertPurchaseOrderSelfLink(orderNumber)
                .withRel(PURCHASE_ORDER_LINK_KEY));

        return resources;
    }

    public Link getGoodsInPurchaseOrderLink(String orderNumber) {
        UriTemplate uriTemplate = new UriTemplate(String.format("%s/%s/goods/{goods}/price-list/{priceList}",
                linkTo(GoodsInPurchaseOrderRestFullEndPoint.class), orderNumber));
        return new Link(uriTemplate, GOODS_IN_PURCHASE_ORDER_LINK_KEY);
    }

    public Link getGoodsInPurchaseOrderSelfLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(GoodsInPurchaseOrderRestFullEndPoint.class)
                .getGoodsDataInPuchaseOrder(orderNumber))
                .withSelfRel();
    }
}
