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

    Goods findGoods(String barCode);

    Goods saveCategoryAttributeValue(String goodsBarCode,
                                     String categoryAttributeName,
                                     String categoryAttributeType,
                                     String categoryAttributeValue);

    Goods updateGoods(Goods account);

    void deleteGoods(String barCode);
}
