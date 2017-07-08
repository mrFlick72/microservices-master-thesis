package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.event.model.GoodsErrorEvent;
import it.valeriovaudi.emarket.event.model.PriceListErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.GoodsNotFoundException;
import it.valeriovaudi.emarket.exception.PriceListNotFoundException;
import it.valeriovaudi.emarket.exception.SaveGoodsException;
import it.valeriovaudi.emarket.exception.SavePriceListException;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsInPriceList;
import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import it.valeriovaudi.emarket.repository.PriceListRepository;
import it.valeriovaudi.emarket.validator.PriceListDataValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;

/**
 * Created by vvaudi on 12/05/17.
 */

@Data
public abstract class AbstractService {

    @Autowired
    protected PriceListDataValidator priceListDataValidator;

    @Autowired
    protected EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    protected PriceListRepository priceListRepository;

    @Autowired
    protected GoodsRepository goodsRepository;

    protected Function<Goods, Map<String, String>> getSafeGoodsAttribute =
            (goods) -> Optional.ofNullable(goods.getGoodsAttribute()).orElse(new HashMap<>());

    protected Function<PriceList, List<GoodsInPriceList>> getSafeGoodsInPriceList =
            (priceList) -> Optional.ofNullable(priceList.getGoodsInPriceList()).orElse(new ArrayList<>());


    protected Goods doSaveGoodsData(String correlationId, Goods goods) {
        Goods goodsAux;
        GoodsErrorEvent goodsErrorEvent;
        try{
            goodsAux = goodsRepository.save(goods);

            eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(),
                    goods.getName(),goods.getBarCode(),goods.getCategory(),EventTypeEnum.SAVE);
        } catch (Exception e){
            e.printStackTrace();
            goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, goods.getId(), goods.getName(),goods.getBarCode(),
                    goods.getCategory(), EventTypeEnum.SAVE, SaveGoodsException.DEFAULT_MESSAGE, SaveGoodsException.class);
            throw  new SaveGoodsException(goodsErrorEvent, SaveGoodsException.DEFAULT_MESSAGE);
        }

        return goodsAux;
    }

    protected PriceList doSavePriceListData(String correlationId, PriceList priceList) {
        PriceList priceListAux;
        PriceListErrorEvent priceListErrorEvent ;
        try{
            priceListAux = priceListRepository.save(priceList);

            eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                    priceList.getName(),EventTypeEnum.SAVE);
        } catch (Exception e){
            priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, priceList.getId(), priceList.getName(),EventTypeEnum.SAVE, SavePriceListException.DEFAULT_MESSAGE, SavePriceListException.class);
            throw  new SavePriceListException(priceListErrorEvent, SavePriceListException.DEFAULT_MESSAGE);
        }

        return priceListAux;
    }

    protected void doCheckGoodsExist(String correlationId, String idGoods) {
        Goods goodsAux;
        Function<String, GoodsNotFoundException> f = userNameAux -> {
            GoodsErrorEvent goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, idGoods,
                    null, null, null, EventTypeEnum.READ, GoodsNotFoundException.DEFAULT_MESSAGE, GoodsNotFoundException.class);
            return new GoodsNotFoundException(goodsErrorEvent, GoodsNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            goodsAux =  goodsRepository.findOne(idGoods);
            if(goodsAux== null){
                throw f.apply(idGoods);
            }
        } catch (Exception e){
            throw f.apply(idGoods);
        }
    }

    protected void doCheckPriceListExist(String correlationId, String idPriceList) {
        PriceList priceListAux;
        Function<String, PriceListNotFoundException> f = userNameAux -> {
            PriceListErrorEvent priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, idPriceList,
                    null, EventTypeEnum.READ, PriceListNotFoundException.DEFAULT_MESSAGE, GoodsNotFoundException.class);
            return new PriceListNotFoundException(priceListErrorEvent, PriceListNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            priceListAux =  priceListRepository.findOne(idPriceList);
            if(priceListAux== null){
                throw f.apply(idPriceList);
            }
        } catch (Exception e){
            throw f.apply(idPriceList);
        }
    }
}
