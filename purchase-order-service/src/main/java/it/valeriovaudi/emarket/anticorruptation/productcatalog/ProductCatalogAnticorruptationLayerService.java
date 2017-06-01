package it.valeriovaudi.emarket.anticorruptation.productcatalog;

import it.valeriovaudi.emarket.anticorruptation.AbstractAntiCorruptationLayerService;
import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
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
public class ProductCatalogAnticorruptationLayerService extends AbstractAntiCorruptationLayerService {

    private Map<String, AnticCorruptationLayerStrategy> productCatalogAntiCorruptationRegistry;

    @Autowired
    private ProductCatalogAntiCorruptationLayerServiceHalJsonStrategy productCatalogAntiCorruptationLayerServiceHalJsonStrategy;

    @PostConstruct
    public void init(){
        productCatalogAntiCorruptationRegistry = new HashMap<>();
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE,productCatalogAntiCorruptationLayerServiceHalJsonStrategy);
    }

    public Goods newGoods(String goods, String mediaType){
        return (Goods) Optional.ofNullable(productCatalogAntiCorruptationRegistry.get(mediaType))
                .map(anticCorruptationLayerStrategy -> anticCorruptationLayerStrategy.traslate(goods))
                .orElse(null);
    }
}
