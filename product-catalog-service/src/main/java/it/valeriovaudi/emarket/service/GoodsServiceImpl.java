package it.valeriovaudi.emarket.service;

import it.valeriovaudi.emarket.model.Goods;
import it.valeriovaudi.emarket.model.GoodsAttributeSchema;
import it.valeriovaudi.emarket.model.GoodsAttributeValue;
import it.valeriovaudi.emarket.repository.GoodsRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vvaudi on 09/05/17.
 */

@Data
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods createGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public List<Goods> findGoodsList() {
        return goodsRepository.findAll();
    }

    // todo
    @Override
    public List<Goods> findGoodsList(String idGoodsCategory) {
        return null;
    }

    @Override
    public Goods findGoods(String idGoods) {
        return goodsRepository.findOne(idGoods);
    }

    @Override
    public Goods saveCategoryAttributeValue(String idGoods, GoodsAttributeValue goodsAttributeValue) {
        Goods goods = goodsRepository.findOne(idGoods);
        List<GoodsAttributeValue> goodsAttributeValues = goods.getGoodsAttributeValues();
        int index = indexOf(goods.getGoodsCategory().getGoodsAttributeSchemaList(), goodsAttributeValue);

        if(index != -1){
            goodsAttributeValues.add(goodsAttributeValue);
        } else {
            goodsAttributeValues.set(index, goodsAttributeValue);
        }
        return goodsRepository.save(goods);
    }

    @Override
    public Goods removeCategoryAttributeValue(String idGoods, GoodsAttributeValue goodsAttributeValue) {
        Goods goods = goodsRepository.findOne(idGoods);
        goods.getGoodsAttributeValues().remove(goodsAttributeValue);
        return goodsRepository.save(goods);
    }

    @Override
    public Goods updateGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public void deleteGoods(String idGoods) {
        goodsRepository.delete(idGoods);
    }

    public int indexOf(List<GoodsAttributeSchema> goodsAttributeSchemaListAux, GoodsAttributeValue goodsAttributeValue){
        GoodsAttributeSchema goodsAttributeSchema = new GoodsAttributeSchema();
        goodsAttributeSchema.setName(goodsAttributeValue.getName());
        goodsAttributeSchema.setType(goodsAttributeValue.getType());
        goodsAttributeSchema.setPattern(goodsAttributeValue.getPattern());
        return goodsAttributeSchemaListAux.indexOf(goodsAttributeSchema);
    }
}
