package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.PriceList;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */


public interface PriceListService {

    PriceList createPriceList(String name);

    List<PriceList> findPriceLists();

    PriceList findPriceList(Long id);

    PriceList saveGoodsInPriceList(Long priceListId, Goods goods, BigDecimal price);
    PriceList removeGoodsInPriceList(Long priceListId, String goodsBarCode);

    PriceList updatePriceList(PriceList priceList);

    void deleteGoods(String barCode);
}
