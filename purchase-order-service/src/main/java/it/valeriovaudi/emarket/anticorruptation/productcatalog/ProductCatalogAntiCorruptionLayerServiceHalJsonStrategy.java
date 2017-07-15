package it.valeriovaudi.emarket.anticorruptation.productcatalog;


import com.fasterxml.jackson.databind.node.ObjectNode;
import it.valeriovaudi.emarket.anticorruptation.AbstractAntiCorruptionLayerStrategy;
import it.valeriovaudi.emarket.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
@Component
public class ProductCatalogAntiCorruptionLayerServiceHalJsonStrategy extends AbstractAntiCorruptionLayerStrategy<Goods> {

    @Override
    public Goods traslate(String body) {
        Goods goods = new Goods();

        try {
            ObjectNode node = (ObjectNode) objectMapper.readTree(body);
            ObjectNode goodsNode = (ObjectNode) node.get("goods");

            Map links = objectMapper.convertValue(node.get("_links"), Map.class);
            String[] split = String.valueOf(links.get("price-list")).split("/");
            String pirceListId = split[split.length-1].substring(0, split[split.length-1].length()-1);

            goods.setPriceListId(pirceListId);
            goods.setId(goodsNode.get("id").asText());
            goods.setBarCode(goodsNode.get("barCode").asText());
            goods.setName(goodsNode.get("name").asText());
            goods.setGoodsAttribute(objectMapper.convertValue(goodsNode.get("goodsAttribute"), Map.class));

            goods.setPrice(new BigDecimal(node.get("price").asDouble()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return goods;
    }
}
