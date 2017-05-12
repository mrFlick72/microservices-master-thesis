package it.valeriovaudi.emarket.hateoas;

import it.valeriovaudi.emarket.endpoint.restfull.GoodsRestFullEndPoint;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by vvaudi on 06/05/17.
 */

@Component
public class GoodsHateoasFactory {

    public Resource<Goods> toResource(Goods goods) {
        Resource<Goods> accountResource = new Resource<>(goods);

        Link selfLink = linkTo(GoodsRestFullEndPoint.class).slash(goods.getId())
                .withSelfRel();

        Link categoryAttributeLink = linkTo(GoodsRestFullEndPoint.class).slash(goods.getId()).slash("category-attribute")
                .withRel("category-attribute");

        accountResource.add(selfLink, categoryAttributeLink);
        return accountResource;
    }

    public Resources<Goods> toResources(List<Goods> goodsList) {
        Resources<Goods> accountResource = new Resources<>(goodsList);

        Link selfRel = linkTo(GoodsRestFullEndPoint.class).withSelfRel();
        accountResource.add(selfRel);

        return accountResource;
    }
}
