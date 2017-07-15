package it.valeriovaudi.emarket.anticorruptation.productcatalog;

import it.valeriovaudi.emarket.anticorruptation.AbstractAntiCorruptionLayerService;
import it.valeriovaudi.emarket.anticorruptation.AntiCorruptionLayerStrategy;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Service
public class ProductCatalogAntiCorruptionLayerService extends AbstractAntiCorruptionLayerService {

    private Map<String, AntiCorruptionLayerStrategy> productCatalogAntiCorruptationRegistry;

    @Autowired
    private ProductCatalogAntiCorruptionLayerServiceHalJsonStrategy productCatalogAntiCorruptionLayerServiceHalJsonStrategy;

    @PostConstruct
    public void init(){
        productCatalogAntiCorruptationRegistry = new HashMap<>();
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE,productCatalogAntiCorruptionLayerServiceHalJsonStrategy);
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE,productCatalogAntiCorruptionLayerServiceHalJsonStrategy);
    }

    public Goods newGoods(String goods, String mediaType){
        return (Goods) Optional.ofNullable(productCatalogAntiCorruptationRegistry.get(mediaType))
                .map(anticCorruptationLayerStrategy -> anticCorruptationLayerStrategy.traslate(goods))
                .orElse(null);
    }
}
