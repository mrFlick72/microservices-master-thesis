package it.valeriovaudi.emarket;

import it.valeriovaudi.emarket.anticorruptation.productcatalog.ProductCatalogAntiCorruptionLayerServiceHalJsonStrategy;
import it.valeriovaudi.emarket.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PurchaseOrderServiceApplicationTests {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	ProductCatalogAntiCorruptionLayerServiceHalJsonStrategy productCatalogAntiCorruptationLayerServiceHalJsonStrategy;

	String goods;

	@Before
	public void setUp(){
		log.info("****************************");
		log.info("****************************");
		log.info("****************************");

		try(InputStream goodsStream = resourceLoader.getResource("classpath:goodsInPriceListResponse.json").getInputStream()){
			goods = StreamUtils.copyToString(goodsStream, Charset.defaultCharset());
		} catch (IOException e) {
			log.info("****************************");
			log.error(e.getMessage());
			log.info("****************************");
		}

		log.info("****************************");
		log.info("****************************");
		log.info("****************************");

	}

	@Test
	public void contextLoads() {
		Goods traslate = productCatalogAntiCorruptationLayerServiceHalJsonStrategy.traslate(goods);
		log.info(traslate.toString());
		Assert.assertNotNull(traslate);
	}

}
