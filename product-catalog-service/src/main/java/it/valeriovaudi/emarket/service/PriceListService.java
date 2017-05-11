package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.PriceList;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mrflick72 on 09/05/17.
 */


public interface PriceListService {
    PriceList createPriceList(PriceList priceList);

    List<PriceList> findPriceLists();

    PriceList findPriceList(String idPriceList);

    PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price);
    PriceList removeGoodsInPriceList(String idPriceList, String idGoods);

    PriceList updatePriceList(PriceList priceList);

    void deletePriceList(String idPriceList);
}
