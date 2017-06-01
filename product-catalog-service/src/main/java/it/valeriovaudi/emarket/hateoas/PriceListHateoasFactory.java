package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.PriceListRestFullEndPoint;
import it.valeriovaudi.emarket.model.PriceList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by vvaudi on 06/05/17.
 */

@Component
public class PriceListHateoasFactory {

    public Resource<PriceList> toResource(PriceList priceList) {
        Resource<PriceList> priceListResource = new Resource<>(priceList);

        Link selfLink = linkTo(PriceListRestFullEndPoint.class).slash(priceList.getId())
                .withSelfRel();

        Link goodsInPriceListLink = linkTo(PriceListRestFullEndPoint.class)
                .slash(priceList.getId()).slash("goods")
                .withRel("goods-list-in-price-list");

        priceListResource.add(selfLink, goodsInPriceListLink);
        return priceListResource;
    }

    public Resources<PriceList> toResources(List<PriceList> priceList) {
        Resources<PriceList> priceListResource = new Resources<>(priceList);

        UriTemplate priceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}",linkTo(PriceListRestFullEndPoint.class).toString()));
        Link priceListLink = new Link(priceListLinkUriTemplate, "price-list");

        UriTemplate goodsListInPriceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}/goods",linkTo(PriceListRestFullEndPoint.class).toString()));
        Link goodsListInPriceListLink = new Link(goodsListInPriceListLinkUriTemplate, "goods-list-in-price-list");

        priceListResource.add(priceListLink,goodsListInPriceListLink);
        return priceListResource;
    }
}
