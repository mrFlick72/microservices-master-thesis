package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.event.model.EventTypeEnum;
import it.valeriovaudi.emarket.model.Goods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vvaudi on 09/05/17.
 */

@Service
public class GoodsServiceImpl extends AbstractService implements GoodsService {

    @Override
    public Goods createGoods(Goods goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);
        Goods save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.CREATE);
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

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Goods removeGoodsAttributeValue(String idGoods, String goodsAttributeKey) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Goods goods = goodsRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);

        stringStringMap.remove(goodsAttributeKey);
        goods.setGoodsAttribute(stringStringMap);

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Goods updateGoods(Goods goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);

        doCheckGoodsExist(correlationId, goods.getId());

        Goods save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.UPDATE);
        return save;
    }

    @Override
    public void deleteGoods(String idGoods) {
        String correlationId = UUID.randomUUID().toString();

        doCheckGoodsExist(correlationId, idGoods);

        Goods one = goodsRepository.findOne(idGoods);
        goodsRepository.delete(idGoods);

        eventDomainPubblishService.publishGoodsEvent(correlationId, idGoods, one.getName(),one.getBarCode(),
                one.getCategory(),EventTypeEnum.DELETE);
    }
}