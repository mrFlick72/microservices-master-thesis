package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;

import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */

public interface GoodsService {
    Goods createGoods(Goods goods);

    List<Goods> findGoodsList();
    List<Goods> findGoodsList(String category);

    Goods findGoods(String idGoods);

    Goods saveGoodsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue);
    Goods removeGoodsAttributeValue(String idGoods, String goodsAttributeKey);

    Goods updateGoods(Goods goods);

    void deleteGoods(String idGoods);
}
