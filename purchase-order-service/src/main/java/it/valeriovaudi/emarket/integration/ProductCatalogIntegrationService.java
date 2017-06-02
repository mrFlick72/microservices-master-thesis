package it.valeriovaudi.emarket.integration;

import it.valeriovaudi.emarket.anticorruptation.productcatalog.ProductCatalogAnticorruptationLayerService;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Service
public class ProductCatalogIntegrationService {

    @Autowired
    private ProductCatalogAnticorruptationLayerService productCatalogAnticorruptationLayerService;

    @Autowired
    private OAuth2RestTemplate productCatalogIntegrationServiceRestTemplate;

    public List<Goods> getPriceListData(String priceListId){

        //....
        return null;
    }

    public Goods getGoodsInPriceListData(String priceListId, String goodsId){
        //....
        return null;
    }
}
