package it.valeriovaudi.emarket.repository;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mrflick72 on 09/05/17.
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsCategoryRepositoryTests {

    @Autowired
    private GoodsService goodsService;

    Goods goods;
    String barCode = UUID.randomUUID().toString();
    String name = "Penne Barilla";
    String category = "generi alimentari";

    @Before
    public void setUp(){
        goods = new Goods();
        goods.setBarCode(barCode);
        goods.setName(name);
        goods.setCategory(category);
    }

    @Test
    public void createGoods(){
        Goods goods = goodsService.createGoods(this.goods);
        Map<String, String> goodsAttribute = new HashMap<>();
        goodsAttribute.put("weight","1Kg");
        goods.setGoodsAttribute(goodsAttribute);
        log.info("goods: " + goods);
    }

/*
    List<Goods> findGoodsList();
    List<Goods> findGoodsList(String category);

    Goods findGoods(String idGoods);

    Goods saveGoodsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue);
    Goods removeGoodsAttributeValue(String idGoods, String goodsAttributeKey);

    Goods updateGoods(Goods goods);

    void deleteGoods(String idGoods);
*/

}