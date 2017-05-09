package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.PriceList;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */


public interface PriceListService {
    PriceList createPriceList(String name);

    List<PriceList> findPriceLists();

    PriceList findPriceList(String priceListId);

    PriceList saveGoodsInPriceList(String priceListId, String goodsId, BigDecimal price);
    PriceList removeGoodsInPriceList(String priceListId, String goodsId);

    PriceList updatePriceList(PriceList priceList);

    void deletePriceList(String priceListId);
}
