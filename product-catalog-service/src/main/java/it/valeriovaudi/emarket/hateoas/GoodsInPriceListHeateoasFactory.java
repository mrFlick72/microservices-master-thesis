package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.PriceListRestFullEndPoint;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by vvaudi on 12/05/17.
 */

@Component
public class GoodsInPriceListHeateoasFactory {

    public Resource<GoodsInPriceList> toResource(String idPriceList, GoodsInPriceList goodsInPriceList) {
        Resource<GoodsInPriceList> priceListResource = new Resource<>(goodsInPriceList);

        Link selfLink = linkTo(PriceListRestFullEndPoint.class)
                .slash(idPriceList).slash("goods")
                .slash(goodsInPriceList.getGoods().getId())
                .withSelfRel();

        Link priceListLink = linkTo(PriceListRestFullEndPoint.class)
                .slash(idPriceList).withRel("price-list");

        priceListResource.add(selfLink, priceListLink);
        return priceListResource;
    }

    public Resources<GoodsInPriceList> toResources(String idPriceList, List<GoodsInPriceList> goodsInPriceList) {
        Resources<GoodsInPriceList> priceListResource = new Resources<>(goodsInPriceList);

        Link selfLink = linkTo(PriceListRestFullEndPoint.class)
                .slash(idPriceList).slash("goods")
                .withSelfRel();

        Link priceListLink = linkTo(PriceListRestFullEndPoint.class)
                .slash(idPriceList).withRel("price-list");

        priceListResource.add(selfLink, priceListLink);

        return priceListResource;
    }
}
