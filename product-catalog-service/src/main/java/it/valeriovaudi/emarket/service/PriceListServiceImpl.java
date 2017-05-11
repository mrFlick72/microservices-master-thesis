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
    public PriceList createPriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    @Override
    public List<PriceList> findPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceList findPriceList(String idPriceList) {
        return priceListRepository.findOne(idPriceList);
    }

    @Override
    public PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price) {
        PriceList priceList = priceListRepository.findOne(idPriceList);
        Goods goods  = goodsRepository.findOne(idGoods);
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
    public PriceList removeGoodsInPriceList(String idPriceList, String idGoods) {
        PriceList priceList = priceListRepository.findOne(idPriceList);
        Goods goods  = goodsRepository.findOne(idGoods);
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
    public void deletePriceList(String idPriceList) {
        priceListRepository.delete(idPriceList);
    }

}
