package it.valeriovaudi.emarket.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.anticorruptation.productcatalog.ProductCatalogAnticorruptationLayerService;
import it.valeriovaudi.emarket.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
@Service
public class ProductCatalogIntegrationService extends AbstractIntegrationService {

    @Autowired
    private ProductCatalogAnticorruptationLayerService productCatalogAnticorruptationLayerService;

    @Autowired
    private OAuth2RestTemplate productCatalogIntegrationServiceRestTemplate;

    @Value("${external-service.base-uri-schema.goods-in-product-catalog}")
    private String goodsInProductCatalogServiceUriSchema;

    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public Goods getGoodsInPriceListData(String priceListId, String goodsId){
        URI uri = UriComponentsBuilder.fromHttpUrl(goodsInProductCatalogServiceUriSchema)
                .buildAndExpand(priceListId, goodsId).toUri();

        ResponseEntity<String> serviceCall =
                productCatalogIntegrationServiceRestTemplate.exchange(newRequestEntity(uri), String.class);

        return productCatalogAnticorruptationLayerService.newGoods(serviceCall.getBody(),
                serviceCall.getHeaders().getContentType().toString());
    }

}
