package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import it.valeriovaudi.emarket.repository.PriceListRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mrflick72 on 10/05/17.
 */


@Data
@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public PriceList createPriceList(String name) {
        PriceList priceList = new PriceList();
        priceList.setName(name);

        return priceListRepository.save(priceList);
    }

    @Override
    public List<PriceList> findPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceList findPriceList(String priceListId) {
        return priceListRepository.findOne(priceListId);
    }

    @Override
    public PriceList saveGoodsInPriceList(String priceListId, String goodsId, BigDecimal price) {
        PriceList priceList = priceListRepository.findOne(priceListId);
        Goods goods  = goodsRepository.findOne(goodsId);
        List<GoodsInPriceList> goodsInPriceListAux = priceList.getGoodsInPriceList();

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
        goodsInPriceList.setPrice(price);

        int index = goodsInPriceListAux.indexOf(goodsInPriceList);

        if(index != -1){
            goodsInPriceListAux.add(goodsInPriceList);
        } else {
            goodsInPriceListAux.set(index, goodsInPriceList);
        }

        return priceListRepository.save(priceList);
    }

    @Override
    public PriceList removeGoodsInPriceList(String priceListId, String goodsId) {
        PriceList priceList = priceListRepository.findOne(priceListId);
        Goods goods  = goodsRepository.findOne(goodsId);
        List<GoodsInPriceList> goodsInPriceListAux = priceList.getGoodsInPriceList();

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
        goodsInPriceListAux.remove(goodsInPriceList);

        return priceListRepository.save(priceList);
    }

    @Override
    public PriceList updatePriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    @Override
    public void deletePriceList(String priceListId) {
        priceListRepository.delete(priceListId);
    }

}
