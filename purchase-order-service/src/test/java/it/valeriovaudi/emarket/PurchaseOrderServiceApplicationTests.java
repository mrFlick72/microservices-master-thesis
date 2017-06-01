package it.valeriovaudi.emarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.valeriovaudi.emarket.anticorruptation.productcatalog.ProductCatalogAntiCorruptationLayerServiceHalJsonStrategy;
import it.valeriovaudi.emarket.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PurchaseOrderServiceApplicationTests {

	String goods = "{\n" +
			"  \"goods\": {\n" +
			"    \"id\": \"59307676c92d7229c2b8d8e2\",\n" +
			"    \"barCode\": \"ASRE2345\",\n" +
			"    \"name\": \"Penne Barilla\",\n" +
			"    \"description\": \"\",\n" +
			"    \"category\": \"food\",\n" +
			"    \"version\": 0,\n" +
			"    \"goodsAttribute\": {\n" +
			"      \"weight\": \"5kg\",\n" +
			"      \"expire-date\": \"5-10-2017\"\n" +
			"    }\n" +
			"  },\n" +
			"  \"price\": 10.5,\n" +
			"  \"_links\": {\n" +
			"    \"self\": {\n" +
			"      \"href\": \"http://localhost:5050/api/v1/product-catalog-service/price-list/5924a1f7bf51d512677ae9c2/goods/59307676c92d7229c2b8d8e2\"\n" +
			"    },\n" +
			"    \"price-list\": {\n" +
			"      \"href\": \"http://localhost:5050/api/v1/product-catalog-service/price-list/5924a1f7bf51d512677ae9c2\"\n" +
			"    }\n" +
			"  }\n" +
			"}";

	@Autowired
	ProductCatalogAntiCorruptationLayerServiceHalJsonStrategy productCatalogAntiCorruptationLayerServiceHalJsonStrategy;


	@Test
	public void contextLoads() {
		Goods traslate = productCatalogAntiCorruptationLayerServiceHalJsonStrategy.traslate(goods);
		log.info(traslate.toString());
		Assert.assertNotNull(traslate);
	}

}
