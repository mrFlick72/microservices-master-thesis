package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.GoodsErrorEvent;
import it.valeriovaudi.emarket.event.model.GoodsEventTypeEnum;
import it.valeriovaudi.emarket.event.service.EventDomainPubblishService;
import it.valeriovaudi.emarket.exception.ConflictSaveGoodsException;
import it.valeriovaudi.emarket.exception.GoodsNotFoundException;
import it.valeriovaudi.emarket.exception.SaveGoodsException;
import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import it.valeriovaudi.emarket.validator.PriceListDataValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;
import java.util.function.Function;

/**
 * Created by vvaudi on 09/05/17.
 */

@Data
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private PriceListDataValidator priceListDataValidator;

    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Override
    public Goods createGoods(Goods goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);
        Goods save = goodsRepository.save(goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), GoodsEventTypeEnum.CREATE);
        return save;
    }

    @Override
    public List<Goods> findGoodsList() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Goods> findGoodsList(String goodsCategory) {
        return goodsRepository.findByCategory(goodsCategory);
    }

    @Override
    public Goods findGoods(String idGoods) {
        doCheckGoodsExist(UUID.randomUUID().toString(), idGoods);
        return goodsRepository.findOne(idGoods);
    }

    @Override
    public Goods saveGoodsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Goods goods = goodsRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);
        stringStringMap.put(goodsAttributeKey, goodsAttributeValue);
        goods.setGoodsAttribute(stringStringMap);

        goodsRepository.save(goods);
        return goods;
    }

    @Override
    public Goods removeGoodsAttributeValue(String idGoods, String goodsAttributeKey) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Goods goods = goodsRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);

        stringStringMap.remove(goodsAttributeKey);
        goods.setGoodsAttribute(stringStringMap);

        goodsRepository.save(goods);
        return goods;
    }

    @Override
    public Goods updateGoods(Goods goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);

        doCheckGoodsExist(correlationId, goods.getId());

        Goods save = goodsRepository.save(goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), GoodsEventTypeEnum.UPDATE);
        return save;
    }

    @Override
    public void deleteGoods(String idGoods) {
        String correlationId = UUID.randomUUID().toString();

        doCheckGoodsExist(correlationId, idGoods);

        Goods one = goodsRepository.findOne(idGoods);
        goodsRepository.delete(idGoods);

        eventDomainPubblishService.publishGoodsEvent(correlationId, idGoods, one.getName(),one.getBarCode(),
                one.getCategory(),GoodsEventTypeEnum.DELETE);
    }

    Function<Goods, Map<String, String>> getSafeGoodsAttribute = (goods) -> Optional.ofNullable(goods.getGoodsAttribute()).orElse(new HashMap<>());

    private Goods doSaveAGoodsData(String correlationId, Goods goods, boolean checkDuplicate) {
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
                            one.getCategory(),GoodsEventTypeEnum.SAVE, ConflictSaveGoodsException.DEFAULT_MESSAGE, ConflictSaveGoodsException.class);
                    conflictSaveGoodsException = new ConflictSaveGoodsException(goodsErrorEvent, ConflictSaveGoodsException.DEFAULT_MESSAGE);
                }
            }else {
                goodsAux = goodsRepository.save(goods);
            }
        } catch (Exception e){
            goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, goods.getId(), goods.getName(),goods.getBarCode(),
                    goods.getCategory(),GoodsEventTypeEnum.SAVE, SaveGoodsException.DEFAULT_MESSAGE, SaveGoodsException.class);
            throw  new SaveGoodsException(goodsErrorEvent, SaveGoodsException.DEFAULT_MESSAGE);
        }

        if(conflictSaveGoodsException!= null){
            throw conflictSaveGoodsException;
        }

        return goodsAux;
    }

    private void doCheckGoodsExist(String correlationId, String idGoods) {
        Goods goodsAux;
        Function<String, GoodsNotFoundException> f = userNameAux -> {
            GoodsErrorEvent goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, idGoods,
                    null, null, null, GoodsEventTypeEnum.READ, GoodsNotFoundException.DEFAULT_MESSAGE, GoodsNotFoundException.class);
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

}