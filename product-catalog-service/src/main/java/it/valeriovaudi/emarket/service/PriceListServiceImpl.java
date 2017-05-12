package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import it.valeriovaudi.emarket.model.PriceList;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by mrflick72 on 10/05/17.
 */

@Service
public class PriceListServiceImpl extends AbstractService implements PriceListService {

    @Override
    public PriceList createPriceList(PriceList priceList) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, priceList);
        PriceList save = doSavePriceListData(correlationId, priceList, true);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.CREATE);
        return save;
    }

    @Override
    public List<PriceList> findPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceList findPriceList(String idPriceList) {
        doCheckPriceListExist(UUID.randomUUID().toString(), idPriceList);
        return priceListRepository.findOne(idPriceList);
    }

    @Override
    public PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        PriceList priceList = priceListRepository.findOne(idPriceList);

        Goods goods  = goodsRepository.findOne(idGoods);
        List<GoodsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
        goodsInPriceList.setPrice(price);

        int index = goodsInPriceListAux.indexOf(goodsInPriceList);

        if(index != -1){
            goodsInPriceListAux.add(goodsInPriceList);
        } else {
            goodsInPriceListAux.set(index, goodsInPriceList);
        }

        PriceList priceListAux = doSavePriceListData(correlationId, priceList, false);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.UPDATE);

        return priceListAux;
    }

    @Override
    public PriceList removeGoodsInPriceList(String idPriceList, String idGoods) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        PriceList priceList = priceListRepository.findOne(idPriceList);
        Goods goods  = goodsRepository.findOne(idGoods);

        List<GoodsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
        goodsInPriceListAux.remove(goodsInPriceList);

        PriceList priceListAux = doSavePriceListData(correlationId, priceList, false);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.UPDATE);

        return priceListAux;
    }

    @Override
    public PriceList updatePriceList(PriceList priceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, correlationId);
        return priceListRepository.save(priceList);
    }

    @Override
    public void deletePriceList(String idPriceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);

        priceListRepository.delete(idPriceList);
    }
}
