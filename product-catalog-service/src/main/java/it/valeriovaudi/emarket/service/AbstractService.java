package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.event.model.GoodsErrorEvent;
import it.valeriovaudi.emarket.event.model.PriceListErrorEvent;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.*;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.PriceList;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import it.valeriovaudi.emarket.repository.PriceListRepository;
import it.valeriovaudi.emarket.validator.PriceListDataValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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

    protected Goods doSaveGoodsData(String correlationId, Goods goods, boolean checkDuplicate) {
        Goods goodsAux = goods;
        ConflictSaveGoodsException conflictSaveGoodsException = null;
        GoodsErrorEvent goodsErrorEvent;
        try{
            if(checkDuplicate){
                Goods one = goodsRepository.findOne(goods.getId());
                if(one == null){
                    goodsAux = goodsRepository.save(goods);
                } else {
                    goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, goods.getId(), one.getName(),one.getBarCode(),
                            one.getCategory(), EventTypeEnum.SAVE, ConflictSaveGoodsException.DEFAULT_MESSAGE, ConflictSaveGoodsException.class);
                    conflictSaveGoodsException = new ConflictSaveGoodsException(goodsErrorEvent, ConflictSaveGoodsException.DEFAULT_MESSAGE);
                }
            }else {
                goodsAux = goodsRepository.save(goods);
            }
        } catch (Exception e){
            goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, goods.getId(), goods.getName(),goods.getBarCode(),
                    goods.getCategory(), EventTypeEnum.SAVE, SaveGoodsException.DEFAULT_MESSAGE, SaveGoodsException.class);
            throw  new SaveGoodsException(goodsErrorEvent, SaveGoodsException.DEFAULT_MESSAGE);
        }

        if(conflictSaveGoodsException!= null){
            throw conflictSaveGoodsException;
        }

        return goodsAux;
    }

    protected PriceList doSavePriceListData(String correlationId, PriceList priceList, boolean checkDuplicate) {
        PriceList priceListAux = priceList;
        ConflictSavePriceListException conflictSavePriceListException = null;
        PriceListErrorEvent priceListErrorEvent ;
        try{
            if(checkDuplicate){
                Goods one = goodsRepository.findOne(priceListAux.getId());
                if(one == null){
                    priceListAux = priceListRepository.save(priceList);
                } else {
                    priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, priceList.getId(), priceList.getName(),EventTypeEnum.SAVE, ConflictSavePriceListException.DEFAULT_MESSAGE, ConflictSavePriceListException.class);
                    conflictSavePriceListException = new ConflictSavePriceListException(priceListErrorEvent, ConflictSavePriceListException.DEFAULT_MESSAGE);
                }
            }else {
                priceListAux = priceListRepository.save(priceList);
            }
        } catch (Exception e){
            priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, priceList.getId(), priceList.getName(),EventTypeEnum.SAVE, SavePriceListException.DEFAULT_MESSAGE, SavePriceListException.class);
            throw  new SaveGoodsException(priceListErrorEvent, SaveGoodsException.DEFAULT_MESSAGE);
        }

        if(conflictSavePriceListException!= null){
            throw conflictSavePriceListException;
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
