package it.valeriovaudi.emarket.anticorruptation.productcatalog;

import it.valeriovaudi.emarket.anticorruptation.AnticCorruptationLayerStrategy;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Service
public class ProductCatalogAnticorruptationLayerService {

    private Map<String, AnticCorruptationLayerStrategy> productCatalogAntiCorruptationRegistry;

    @PostConstruct
    public void init(){
        productCatalogAntiCorruptationRegistry = new HashMap<>();
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, new ProductCatalogAnticorruptationLayerServiceHalJsonStrategy());
    }

    public Goods newGoods(String goods, String mediaType){
        throw new UnsupportedOperationException();
    }
}
