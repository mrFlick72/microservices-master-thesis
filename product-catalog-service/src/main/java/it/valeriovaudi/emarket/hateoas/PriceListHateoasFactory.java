package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.GoodsRestFullEndPoint;
import it.valeriovaudi.emarket.endpoint.restfull.PriceListRestFullEndPoint;
import it.valeriovaudi.emarket.model.Goods;
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
        Resource<PriceList> accountResource = new Resource<>(priceList);

        Link selfLink = linkTo(PriceListRestFullEndPoint.class).slash(priceList.getId())
                .withSelfRel();

        Link goodsInPriceListLink = linkTo(GoodsRestFullEndPoint.class).slash(priceList.getId())
                .slash(priceList.getId()).slash("goods")
                .withRel("goods-list-in-price-list");

        accountResource.add(selfLink, goodsInPriceListLink);
        return accountResource;
    }

    public Resources<Goods> toResources(List<Goods> goodsList) {
        Resources<Goods> accountResource = new Resources<>(goodsList);

        UriTemplate priceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}",linkTo(PriceListRestFullEndPoint.class).toString()));
        Link priceListLink = new Link(priceListLinkUriTemplate, "price-list");

        UriTemplate goodsListInPriceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}/goods",linkTo(PriceListRestFullEndPoint.class).toString()));
        Link goodsListInPriceListLink = new Link(goodsListInPriceListLinkUriTemplate, "goods-list-in-price-list");

        accountResource.add(priceListLink,goodsListInPriceListLink);
        return accountResource;
    }
}
