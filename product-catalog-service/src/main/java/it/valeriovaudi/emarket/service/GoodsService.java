package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsAttributeValue;

import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsService {
    Goods createGoods(Goods goods);

    List<Goods> findGoodsList();
    List<Goods> findGoodsList(String idGoodsCategory);

    Goods findGoods(String idGoods);

    Goods saveCategoryAttributeValue(String idGoods, GoodsAttributeValue goodsAttributeValue);
    Goods removeCategoryAttributeValue(String idGoods, GoodsAttributeValue goodsAttributeValue);

    Goods updateGoods(Goods goods);

    void deleteGoods(String idGoods);
}